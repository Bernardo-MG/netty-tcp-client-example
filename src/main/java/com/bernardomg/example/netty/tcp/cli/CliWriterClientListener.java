/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2023 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.netty.tcp.cli;

import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

import com.bernardomg.example.netty.tcp.client.ClientListener;

/**
 * Client listener which will write the context of each step into the CLI console.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public final class CliWriterClientListener implements ClientListener {

    /**
     * Host for the server to which this client will connect.
     */
    private final String      host;

    /**
     * Port for the server to which this client will connect.
     */
    private final Integer     port;

    /**
     * CLI writer, to print console messages.
     */
    private final PrintWriter writer;

    public CliWriterClientListener(final String hst, final Integer prt, final PrintWriter wrt) {
        super();

        port = Objects.requireNonNull(prt);
        host = Objects.requireNonNull(hst);
        writer = Objects.requireNonNull(wrt);
    }

    @Override
    public final void onClose() {
        writer.println("------------");
        writer.println("Closing connection");
        writer.println("------------");
    }

    @Override
    public final void onConnect() {
        writer.println("------------");
        writer.printf("Opening connection to %s:%d", host, port);
        writer.println();
        writer.println("------------");
    }

    @Override
    public final void onRequest(final String request, final Optional<String> response, final Boolean success) {
        // Prints the final result
        writer.println();
        writer.println("------------");
        writer.printf("Sending message %s", request);
        writer.println();
        writer.println("------------");

        if (success) {
            writer.println("Sent message successfully");
        } else {
            writer.println("Failed sending message");
        }

        if (response.isEmpty()) {
            writer.println("Received no response");
        } else {
            writer.printf("Received response: %s", response.get());
            writer.println();
        }
    }

}