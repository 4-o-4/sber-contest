package task.beautifulcode.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckBracketsTest {
    private final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    private final HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

    @Test
    public void postRequestToCheckBracketsServletReturnedAsJson() throws IOException {
        byte[] json = "{\"text\": \"Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями.\"}".getBytes();
        MockServletInputStream inputStream = new MockServletInputStream(json);
        when(httpServletRequest.getInputStream()).thenReturn(inputStream);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        CheckBrackets checkBrackets = new CheckBrackets();
        checkBrackets.doPost(httpServletRequest, httpServletResponse);

        String response = "true";
        String jsonString = sw.getBuffer().toString();
        assertEquals(
                String.format("{\n  \"isCorrect\" : %s\n}\n", response),
                jsonString);
    }

    @Test
    public void postRequestToCheckBracketsServletReturnedAsEmpty() throws IOException {
        byte[] json = "{\"text\": \"\"}".getBytes();
        MockServletInputStream inputStream = new MockServletInputStream(json);
        when(httpServletRequest.getInputStream()).thenReturn(inputStream);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(httpServletResponse.getWriter()).thenReturn(pw);

        CheckBrackets checkBrackets = new CheckBrackets();
        checkBrackets.doPost(httpServletRequest, httpServletResponse);

        String jsonString = sw.getBuffer().toString();
        assertEquals("", jsonString);
    }

    private static class MockServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream inputStream;

        public MockServletInputStream(byte[] json) {
            this.inputStream = new ByteArrayInputStream(json);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return inputStream.read(b);
        }
    }
}