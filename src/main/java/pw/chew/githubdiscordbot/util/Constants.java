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

import java.util.regex.Pattern;

/**
 * Simple class to store stuff we need to use around the bot
 */
public class Constants {
    // Patterns for matching
    public static final Pattern USER = Pattern.compile("https?://github\\.com/([0-9a-zA-Z]+)", 0);
    public static final Pattern REPO = Pattern.compile("https?://github\\.com/([0-9a-zA-Z]+)/([0-9a-zA-Z]+)", 0);
    public static final Pattern ISSUE = Pattern.compile("https?://github\\.com/([0-9a-zA-Z]+)/([0-9a-zA-Z]+)/issues/([0-9]+)", 0);
    public static final Pattern PULL_REQUEST = Pattern.compile("https?://github\\.com/([0-9a-zA-Z]+)/([0-9a-zA-Z]+)/pulls/([0-9]+)", 0);
    public static final Pattern COMMIT = Pattern.compile("https?://github\\.com/(.+)/(.+)/commit/(.+)", 0);
    public static final Pattern ISSUE_COMMENT = Pattern.compile("https?://github\\.com/(.+)/(.+)/issues/(.+)#issuecomment-(.+)", 0);
}
