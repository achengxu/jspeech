package com.achengxu.speech;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TTS extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String GURL = "http://translate.google.com/translate_tts?q=";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		speak(request, response);
	}

	public void speak(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String text = request.getParameter("text");
		String clientUrl = request.getScheme() + "://";
		clientUrl += request.getHeader("host");
		clientUrl += request.getRequestURI();
		if (request.getQueryString() != null)
			clientUrl += "?" + request.getQueryString();
		if (null == text)
			return;
		URL url = new URL(GURL + text.replace(" ", "%20") + "&tl=en");

		URLConnection urlConn = url.openConnection();

		urlConn.addRequestProperty("User-Agent", "Mozilla");

		InputStream audioSrc = urlConn.getInputStream();

		DataInputStream read = new DataInputStream(audioSrc);

		ServletOutputStream out = response.getOutputStream();

		int b = 0;
		int c;

		while ((c = read.read()) != -1) {
			out.write(c);
			b++;
		}
		read.close();
		audioSrc.close();
		response.setContentType("audio/mpeg");
		response.setContentLength(b);
		out.flush();
		out.close();
	}
}