/*
 * Copyright (c) 2021-2021 Chewbotcca. https://chew.pw/chewbotcca
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author Chewbotcca
 * @link https://github.com/Chewbotcca/GitHubDiscordBot
 */
package pw.chew.githubdiscordbot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.chew.githubdiscordbot.util.Constants;
import pw.chew.githubdiscordbot.util.ContentBuilders;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubMessageListener extends ListenerAdapter {
    private final GitHub github;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GitHubMessageListener(GitHub github) {
        this.github = github;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; // Ignore bots

        String message = event.getMessage().getContentRaw();

        // Don't even bother if there's no GitHub in the message
        if (!message.contains("github.com")) return;

        Pattern pattern = attemptMatch(message);
        if (pattern == null) return; // No matches found

        try {
            Matcher matcher = pattern.matcher(message);
            if (!matcher.find()) return; // Search for matches
            String[] groups = new String[matcher.groupCount()];
            for (int i = 0; i < matcher.groupCount(); i++) {
                groups[i] = matcher.group(i + 1);
            }

            if (pattern == Constants.REPO) {
                String name = groups[0] + "/" + groups[1];

                GHRepository repo = github.getRepository(name);

                event.getMessage().replyEmbeds(ContentBuilders.buildRepoEmbed(repo)).queue(response -> {
                    // Attempt to suppress embed and use ours instead
                    if (event.isFromGuild()) {
                        event.getMessage().suppressEmbeds(true).queue(unused -> {
                        }, throwable -> {
                            logger.debug("Unable to suppress embed due to " + throwable.getMessage());
                        });
                    }
                });
            }
        } catch (IOException exception) {
            exception.printStackTrace(); // Just in case
            return; // Fail silently
        }
    }

    /**
     * Attempt to match the message with a Pattern, from which we can retrieve and embed data
     *
     * @param content the message content
     * @return a possibly-null pattern
     */
    private Pattern attemptMatch(String content) {
        if (Constants.REPO.matcher(content).matches()) {
            return Constants.REPO;
        }
        return null;
    }
}
