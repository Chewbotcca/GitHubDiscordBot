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
package pw.chew.githubdiscordbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.chew.githubdiscordbot.commands.BaseCommand;
import pw.chew.githubdiscordbot.listeners.GitHubMessageListener;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GitHubDiscordBot {
    // Instance Variables
    private static final Logger logger = LoggerFactory.getLogger(GitHubDiscordBot.class);
    private static final Properties properties = new Properties();
    private static EventWaiter waiter;
    private static CommandClient client;
    private static GitHub github;
    private static JDA jda;

    public static void main(String[] args) throws IOException, LoginException {
        // Load the properties
        properties.load(new FileInputStream("bot.properties"));

        // Initialize EventWaiter and the CommandClient
        waiter = new EventWaiter();
        CommandClientBuilder builder = new CommandClientBuilder();

        // Build the client
        builder.useDefaultGame();
        builder.setOwnerId(properties.getProperty("owner-id"));
        builder.setPrefix(properties.getProperty("prefix"));
        builder.useHelpBuilder(false);

        // Initialize GitHub
        github = new GitHubBuilder().withOAuthToken(properties.getProperty("github-token")).build();

        // If we specify a server ID, only upsert slash commands in there
        String forcedGuildId = properties.getProperty("dev-server-id");
        if (!forcedGuildId.isBlank()) {
            builder.forceGuildOnly(forcedGuildId);
        }

        // Add commands
        builder.addCommand(new BaseCommand(github));
        builder.addSlashCommand(new BaseCommand(github));

        // Build the client
        client = builder.build();

        // Register JDA
        jda = JDABuilder.createDefault(properties.getProperty("token"))
            .setStatus(OnlineStatus.ONLINE)
            .setActivity(Activity.playing("Booting"))
            .addEventListeners(
                client,
                waiter,
                new GitHubMessageListener(github)
            ).build();
    }
}
