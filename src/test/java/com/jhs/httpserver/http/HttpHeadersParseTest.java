package com.jhs.httpserver.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpHeadersParseTest {
    private HttpParser httpParser;
    private Method parseHeaderMethod;

    @BeforeAll
    public void beforeClass() throws NoSuchMethodException {
        httpParser = new HttpParser();
        Class<HttpParser> cls = HttpParser.class;
        parseHeaderMethod = cls.getDeclaredMethod("parseHeaders", InputStreamReader.class, HttpRequest.class);

        parseHeaderMethod.setAccessible(true);
    }

    public void testSingleHeader() throws InvocationTargetException, IllegalAccessException {
        HttpRequest request = new HttpRequest();
        parseHeaderMethod.invoke(
                httpParser,
                generatedSingleheaderMessage(),
                request
        );
    }

    private InputStreamReader generatedSingleheaderMessage() {
        String rawData = "Host: localhost:49312\r\n" +
//                "Connection: keep-alive\r\n" +
//                "sec-ch-ua: \"Chromium\";v=\"148\", \"Google Chrome\";v=\"148\", \"Not/A)Brand\";v=\"99\"\r\n" +
//                "sec-ch-ua-mobile: ?0\r\n" +
//                "sec-ch-ua-platform: \"Windows\"\r\n" +
//                "Upgrade-Insecure-Requests: 1\r\n" +
//                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/148.0.0.0 Safari/537.36\r\n" +
//                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
//                "Sec-Fetch-Site: none\r\n" +
//                "Sec-Fetch-Mode: navigate\r\n" +
//                "Sec-Fetch-User: ?1\r\n" +
//                "Sec-Fetch-Dest: document\r\n" +
//                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
//                "Accept-Language: en-US,en;q=0.9\r\n" +
                "\r\n";
        InputStream inputStream =  new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        // tools.ietf.org/html/rfc7231
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        return reader;
    }

}
