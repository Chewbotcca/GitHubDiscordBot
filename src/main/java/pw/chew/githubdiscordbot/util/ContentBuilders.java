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
package pw.chew.githubdiscordbot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHRepository;

/**
 * Simple class of embed builders given specific objects to fill in
 */
public class ContentBuilders {
    /**
     * Builds an embed with the given repository data
     *
     * @param repository the repository to get data from
     * @return a completed embed
     */
    public static MessageEmbed buildRepoEmbed(@NotNull GHRepository repository) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(repository.getFullName());
        embed.setDescription(repository.getDescription());

        if (!repository.getHomepage().isBlank()) {
            embed.addField("Website", repository.getHomepage(), true);
        }

        if (repository.getStargazersCount() > 0) {
            embed.addField("Stars", repository.getStargazersCount() + "", true);
        }

        return embed.build();
    }
}
