//package com.zshop.helper.utils;
//
//import java.io.IOException;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import com.zshop.commons.exception.business.BusinessException;
//
//@RunWith(PowerMockRunner.class)
//public class ServiceUtilsTest {
//
//	@InjectMocks
//	private ServiceUtils serviceUtils;
//	
//	private static final String url = "url";
//	private static final String reqBody = "request";
//	private static final String TEST_RESPONSE = "test\nresponse";
//    private static final int PORT = 8080;
//    private static final String HOSTNAME = "hostname";
//    private static final String PROTOCOL = "http";
//    private static final String REFERENCE = "REFERENCE";
//    private HttpURLConnection connection;
//
//    /**
//     * Our output.
//     */
//    private ByteArrayOutputStream output;
//
//    /**
//     * Our input.
//     */
//    private ByteArrayInputStream input;
//
//    /**
//     * Instance under tests.
//     */
//    private toTest instance;
//	@Test
//	public void shouldTestPost() throws BusinessException, IOException {
//		ServiceUtils.post(url, reqBody);
//	}
//
//	@Test
//	public void shouldTestGet() throws BusinessException, IOException {
//		ServiceUtils.get(url);
//	}
//	
//	@Test
//	public void shouldTestPut() throws BusinessException, IOException {
//		ServiceUtils.put(url, reqBody);
//	}
//}
