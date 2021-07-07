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
package pw.chew.githubdiscordbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.kohsuke.github.GitHub;

/**
 * The base command of the bot. Every single sub-command is derived from this very command.
 * This utilizes children to link to and add to the bot's command chain.
 */
public class BaseCommand extends SlashCommand {
    /**
     * Constructor to define the important things
     */
    public BaseCommand(GitHub github) {
        // Internal sets for command client
        this.name = "github";
        this.help = "Home base of all GitHub commands. Ready for launch!"; // This message will never be shown to users
        this.aliases = new String[]{"gh"};
        this.guildOnly = false;

        // Children
        this.children = new SlashCommand[]{};
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        // This doesn't matter as we won't be replying from here directly
    }
}
