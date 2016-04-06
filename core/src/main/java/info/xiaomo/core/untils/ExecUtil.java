/**
 * 
 */
package info.xiaomo.core.untils;

/**
 * @author advance
 *
 */
/*
 * Convenience methods for executing non-Java processes.
 * Copyright (C) 2005 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Java+Utilities
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See COPYING.TXT for details.
 */

import java.io.*;

/**
 * Convenience methods for executing non-Java processes.
 * More information about this class is available from <a target="_top" href=
 * "http://ostermiller.org/utils/ExecHelper.html">ostermiller.org</a>.
 *
 * @author Stephen Ostermiller http://ostermiller.org/contact.pl?regarding=Java+Utilities
 * @since ostermillerutils 1.06.00
 */
public final class ExecUtil {

	/**
	 * Executes the specified command and arguments in a separate process, and waits for the
	 * process to finish.
	 * <p>
	 * Output from the process is expected to be text in the system's default character set.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param cmdarray array containing the command to call and its arguments.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil exec(String[] cmdarray) throws IOException {
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray), null);
	}

	/**
	 * Executes the specified command and arguments in a separate process, and waits for the
	 * process to finish.
	 * <p>
	 * Output from the process is expected to be text in the system's default character set.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param cmdarray array containing the command to call and its arguments.
	 * @param envp array of strings, each element of which has environment variable settings in format name=value.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil exec(String[] cmdarray, String[] envp) throws IOException {
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray, envp), null);
	}

	/**
	 * Executes the specified command and arguments in a separate process, and waits for the
	 * process to finish.
	 * <p>
	 * Output from the process is expected to be text in the system's default character set.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param cmdarray array containing the command to call and its arguments.
	 * @param envp array of strings, each element of which has environment variable settings in format name=value.
	 * @param dir the working directory of the subprocess, or null if the subprocess should inherit the working directory of the current process.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil exec(String[] cmdarray, String[] envp, File dir) throws IOException {
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray, envp), null);
	}

	/**
	 * Executes the specified command and arguments in a separate process, and waits for the
	 * process to finish.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param cmdarray array containing the command to call and its arguments.
	 * @param charset Output from the executed command is expected to be in this character set.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil exec(String[] cmdarray, String charset) throws IOException {
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray), charset);
	}

	/**
	 * Executes the specified command and arguments in a separate process, and waits for the
	 * process to finish.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param cmdarray array containing the command to call and its arguments.
	 * @param envp array of strings, each element of which has environment variable settings in format name=value.
	 * @param charset Output from the executed command is expected to be in this character set.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil exec(String[] cmdarray, String[] envp, String charset) throws IOException {
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray, envp), charset);
	}

	/**
	 * Executes the specified command and arguments in a separate process, and waits for the
	 * process to finish.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param cmdarray array containing the command to call and its arguments.
	 * @param envp array of strings, each element of which has environment variable settings in format name=value.
	 * @param dir the working directory of the subprocess, or null if the subprocess should inherit the working directory of the current process.
	 * @param charset Output from the executed command is expected to be in this character set.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil exec(String[] cmdarray, String[] envp, File dir, String charset) throws IOException {
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray, envp), charset);
	}

	/**
	 * Executes the specified command using a shell.  On windows uses cmd.exe or command.exe.
	 * On other platforms it uses /bin/sh.
	 * <p>
	 * A shell should be used to execute commands when features such as file redirection, pipes,
	 * argument parsing are desired.
	 * <p>
	 * Output from the process is expected to be text in the system's default character set.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param command String containing a command to be parsed by the shell and executed.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if command is null
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil execUsingShell(String command) throws IOException {
		return execUsingShell(command, null);
	}

	/**
	 * Executes the specified command using a shell.  On windows uses cmd.exe or command.exe.
	 * On other platforms it uses /bin/sh.
	 * <p>
	 * A shell should be used to execute commands when features such as file redirection, pipes,
	 * argument parsing are desired.
	 * <p>
	 * No input is passed to the process on STDIN.
	 *
	 * @param command String containing a command to be parsed by the shell and executed.
	 * @param charset Output from the executed command is expected to be in this character set.
	 * @return The results of the execution in an ExecUtil object.
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if command is null
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public static ExecUtil execUsingShell(String command, String charset) throws IOException {
		if (command == null) throw new NullPointerException();
		String[] cmdarray;
		String os = System.getProperty("os.name");
		if (os.equals("Windows 95") || os.equals("Windows 98") || os.equals("Windows ME")){
			cmdarray = new String[]{"command.exe", "/C", command};
		} else if (os.startsWith("Windows")){
			cmdarray = new String[]{"cmd.exe", "/C", command};
		} else {
			cmdarray = new String[]{"/bin/sh", "-c", command};
		}
		return new ExecUtil(Runtime.getRuntime().exec(cmdarray), charset);
	}

	/**
	 * Take a process, record its standard error and standard out streams, wait for it to finish
	 *
	 * @param process process to watch
	 * @throws SecurityException if a security manager exists and its checkExec method doesn't allow creation of a subprocess.
	 * @throws IOException - if an I/O error occurs
	 * @throws NullPointerException - if cmdarray is null
	 * @throws IndexOutOfBoundsException - if cmdarray is an empty array (has length 0).
	 *
	 * @since ostermillerutils 1.06.00
	 */
	private ExecUtil(Process process, String charset) throws IOException {
		StringBuffer output = new StringBuffer();
		StringBuffer error = new StringBuffer();

		Reader stdout;
		Reader stderr;

		if (charset == null){
			// This is one time that the system charset is appropriate,
			// don't specify a character set.
			stdout = new InputStreamReader(process.getInputStream());
			stderr = new InputStreamReader(process.getErrorStream());
		} else {
			stdout = new InputStreamReader(process.getInputStream(), charset);
			stderr = new InputStreamReader(process.getErrorStream(), charset);
		}
		char[] buffer = new char[1024];

		boolean done = false;
		boolean stdoutclosed = false;
		boolean stderrclosed = false;
		while (!done){
			boolean readSomething = false;
			// read from the process's standard output
			if (!stdoutclosed && stdout.ready()){
				readSomething = true;
				int read = stdout.read(buffer, 0, buffer.length);
				if (read < 0){
					readSomething = true;
					stdoutclosed = true;
				} else if (read > 0){
					readSomething = true;
					output.append(buffer, 0, read);
				}
			}
			// read from the process's standard error
			if (!stderrclosed && stderr.ready()){
				int read = stderr.read(buffer, 0, buffer.length);
				if (read < 0){
					readSomething = true;
					stderrclosed = true;
				} else if (read > 0){
					readSomething = true;
					error.append(buffer, 0, read);
				}
			}
			// Check the exit status only we haven't read anything,
			// if something has been read, the process is obviously not dead yet.
			if (!readSomething){
				try {
					this.status = process.exitValue();
					done = true;
				} catch (IllegalThreadStateException itx){
					// Exit status not ready yet.
					// Give the process a little breathing room.
					try {
						Thread.sleep(100);
					} catch (InterruptedException ix){
						process.destroy();
						throw new IOException("Interrupted - processes killed");
					}
				}
			}
		}

		this.output = output.toString();
		this.error = error.toString();
	}

	/**
	 * The output of the job that ran.
	 *
	 * @since ostermillerutils 1.06.00
	 */
	private String output;

	/**
	 * Get the output of the job that ran.
	 *
	 * @return Everything the executed process wrote to its standard output as a String.
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public String getOutput(){
		return output;
	}

	/**
	 * The error output of the job that ran.
	 *
	 * @since ostermillerutils 1.06.00
	 */
	private String error;

	/**
	 * Get the error output of the job that ran.
	 *
	 * @return Everything the executed process wrote to its standard error as a String.
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public String getError(){
		return error;
	}

	/**
	 * The status of the job that ran.
	 *
	 * @since ostermillerutils 1.06.00
	 */
	private int status;

	/**
	 * Get the status of the job that ran.
	 *
	 * @return exit status of the executed process, by convention, the value 0 indicates normal termination.
	 *
	 * @since ostermillerutils 1.06.00
	 */
	public int getStatus(){
		return status;
	}
}

