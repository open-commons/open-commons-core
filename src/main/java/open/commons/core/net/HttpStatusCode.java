/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
* 
*
* This file is generated under this project, "open-commons-core".
*
* Date  : 2014. 6. 24. 오후 5:45:42
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.net;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * Referenced in <a href="http://en.wikipedia.org/wiki/List_of_HTTP_status_codes">http://en.wikipedia.org/wiki/
 * List_of_HTTP_status_codes</a>
 * 
 * @since 2014. 6. 24.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class HttpStatusCode implements Cloneable {

    private static ConcurrentHashMap<Integer, HttpStatusCode> httpStatusCodes = new ConcurrentHashMap<Integer, HttpStatusCode>();

    static {

        httpStatusCodes.put(100, new HttpStatusCode(100, "Continue",
                "This means that the server has received the request headers, and that the client should proceed to send the request body (in the case of a request for which a body needs to be sent; for example, a POST request). If the request body is large, sending it to a server when a request has already been rejected based upon inappropriate headers is inefficient. To have a server check if the request could be accepted based on the request's headers alone, a client must send Expect: 100-continue as a header in its initial request and check if a 100 Continue status code is received in response before continuing (or receive 417 Expectation Failed and not continue).",
                "Informational",
                "Request received, continuing process. This class of status code indicates a provisional response, consisting only of the Status-Line and optional headers, and is terminated by an empty line. Since HTTP/1.0 did not define any 1xx status codes, servers must not send a 1xx response to an HTTP/1.0 client except under experimental conditions."));
        httpStatusCodes.put(101, new HttpStatusCode(101, "Switching Protocols",
                "This means the requester has asked the server to switch protocols and the server is acknowledging that it will do so.", "Informational",
                "Request received, continuing process. This class of status code indicates a provisional response, consisting only of the Status-Line and optional headers, and is terminated by an empty line. Since HTTP/1.0 did not define any 1xx status codes, servers must not send a 1xx response to an HTTP/1.0 client except under experimental conditions."));
        httpStatusCodes.put(102, new HttpStatusCode(102, "Processing (WebDAV; RFC 2518)",
                "As a WebDAV request may contain many sub-requests involving file operations, it may take a long time to complete the request. This code indicates that the server has received and is processing the request, but no response is available yet. This prevents the client from timing out and assuming the request was lost.",
                "Informational",
                "Request received, continuing process. This class of status code indicates a provisional response, consisting only of the Status-Line and optional headers, and is terminated by an empty line. Since HTTP/1.0 did not define any 1xx status codes, servers must not send a 1xx response to an HTTP/1.0 client except under experimental conditions."));
        httpStatusCodes.put(200, new HttpStatusCode(200, "OK",
                "Standard response for successful HTTP requests. The actual response will depend on the request method used. In a GET request, the response will contain an entity corresponding to the requested resource. In a POST request the response will contain an entity describing or containing the result of the action.",
                "Success", "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(201, new HttpStatusCode(201, "Created", "The request has been fulfilled and resulted in a new resource being created.", "Success",
                "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(202, new HttpStatusCode(202, "Accepted",
                "The request has been accepted for processing, but the processing has not been completed. The request might or might not eventually be acted upon, as it might be disallowed when processing actually takes place.",
                "Success", "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(203,
                new HttpStatusCode(203, "Non-Authoritative Information (since HTTP/1.1)",
                        "The server successfully processed the request, but is returning information that may be from another source.", "Success",
                        "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(204,
                new HttpStatusCode(204, "No Content",
                        "The server successfully processed the request, but is not returning any content. Usually used as a response to a successful delete request.", "Success",
                        "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(205, new HttpStatusCode(205, "Reset Content",
                "The server successfully processed the request, but is not returning any content. Unlike a 204 response, this response requires that the requester reset the document view.",
                "Success", "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(206, new HttpStatusCode(206, "Partial Content",
                "The server is delivering only part of the resource due to a range header sent by the client. The range header is used by tools like wget to enable resuming of interrupted downloads, or split a download into multiple simultaneous streams.",
                "Success", "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(207,
                new HttpStatusCode(207, "Multi-Status (WebDAV; RFC 4918)",
                        "The message body that follows is an XML message and can contain a number of separate response codes, depending on how many sub-requests were made.",
                        "Success", "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(208,
                new HttpStatusCode(208, "Already Reported (WebDAV; RFC 5842)",
                        "The members of a DAV binding have already been enumerated in a previous reply to this request, and are not being included again.", "Success",
                        "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(226, new HttpStatusCode(226, "IM Used (RFC 3229)",
                "The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance.",
                "Success", "This class of status codes indicates the action requested by the client was received, understood, accepted and processed successfully."));
        httpStatusCodes.put(300, new HttpStatusCode(300, "Multiple Choices",
                "Indicates multiple options for the resource that the client may follow. It, for instance, could be used to present different format options for video, list files with different extensions, or word sense disambiguation.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(301, new HttpStatusCode(301, "Moved Permanently", "This and all future requests should be directed to the given URI.", "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(302, new HttpStatusCode(302, "Found",
                "This is an example of industry practice contradicting the standard. The HTTP/1.0 specification (RFC 1945) required the client to perform a temporary redirect (the original describing phrase was \"Moved Temporarily\"), but popular browsers implemented 302 with the functionality of a 303 See Other. Therefore, HTTP/1.1 added status codes 303 and 307 to distinguish between the two behaviours. However, some Web applications and frameworks use the 302 status code as if it were the 303.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(303, new HttpStatusCode(303, "See Other (since HTTP/1.1)",
                "The response to the request can be found under another URI using a GET method. When received in response to a POST (or PUT/DELETE), it should be assumed that the server has received the data and the redirect should be issued with a separate GET message.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(304, new HttpStatusCode(304, "Not Modified",
                "Indicates that the resource has not been modified since the version specified by the request headers If-Modified-Since or If-Match. This means that there is no need to retransmit the resource, since the client still has a previously-downloaded copy.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(305, new HttpStatusCode(305, "Use Proxy (since HTTP/1.1)",
                "The requested resource is only available through a proxy, whose address is provided in the response. Many HTTP clients (such as Mozilla and Internet Explorer) do not correctly handle responses with this status code, primarily for security reasons.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(306, new HttpStatusCode(306, "Switch Proxy", "No longer used. Originally meant \"Subsequent requests should use the specified proxy.\"", "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(307, new HttpStatusCode(307, "Temporary Redirect (since HTTP/1.1)",
                "In this case, the request should be repeated with another URI; however, future requests should still use the original URI. In contrast to how 302 was historically implemented, the request method is not allowed to be changed when reissuing the original request. For instance, a POST request should be repeated using another POST request.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(308, new HttpStatusCode(308, "Permanent Redirect (Experimental RFC; RFC 7238)",
                "The request, and all future requests should be repeated using another URI. 307 and 308 (as proposed) parallel the behaviours of 302 and 301, but do not allow the HTTP method to change. So, for example, submitting a form to a permanently redirected resource may continue smoothly.",
                "Redirection",
                "The client must take additional action to complete the request. This class of status code indicates that further action needs to be taken by the user agent to fulfill the request. The action required may be carried out by the user agent without interaction with the user if and only if the method used in the second request is GET or HEAD. A user agent should not automatically redirect a request more than five times, since such redirections usually indicate an infinite loop."));
        httpStatusCodes.put(400, new HttpStatusCode(400, "Bad Request", "The request cannot be fulfilled due to bad syntax.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(401, new HttpStatusCode(401, "Unauthorized",
                "Similar to 403 Forbidden, but specifically for use when authentication is required and has failed or has not yet been provided. The response must include a WWW-Authenticate header field containing a challenge applicable to the requested resource. See Basic access authentication and Digest access authentication.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(402, new HttpStatusCode(402, "Payment Required",
                "Reserved for future use. The original intention was that this code might be used as part of some form of digital cash or micropayment scheme, but that has not happened, and this code is not usually used. YouTube uses this status if a particular IP address has made excessive requests, and requires the person to enter a CAPTCHA.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(403, new HttpStatusCode(403, "Forbidden",
                "The request was a valid request, but the server is refusing to respond to it. Unlike a 401 Unauthorized response, authenticating will make no difference.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(404, new HttpStatusCode(404, "Not Found",
                "The requested resource could not be found but may be available again in the future. Subsequent requests by the client are permissible.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(405, new HttpStatusCode(405, "Method Not Allowed",
                "A request was made of a resource using a request method not supported by that resource; for example, using GET on a form which requires data to be presented via POST, or using PUT on a read-only resource.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(406, new HttpStatusCode(406, "Not Acceptable",
                "The requested resource is only capable of generating content not acceptable according to the Accept headers sent in the request.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(407, new HttpStatusCode(407, "Proxy Authentication Required", "The client must first authenticate itself with the proxy.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(408, new HttpStatusCode(408, "Request Timeout",
                "The server timed out waiting for the request. According to HTTP specifications: \"The client did not produce a request within the time that the server was prepared to wait. The client MAY repeat the request without modifications at any later time.\"",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(409, new HttpStatusCode(409, "Conflict",
                "Indicates that the request could not be processed because of conflict in the request, such as an edit conflict in the case of multiple updates.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(410, new HttpStatusCode(410, "Gone",
                "Indicates that the resource requested is no longer available and will not be available again. This should be used when a resource has been intentionally removed and the resource should be purged. Upon receiving a 410 status code, the client should not request the resource again in the future. Clients such as search engines should remove the resource from their indices.  Most use cases do not require clients and search engines to purge the resource, and a \"404 Not Found\" may be used instead.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(411, new HttpStatusCode(411, "Length Required", "The request did not specify the length of its content, which is required by the requested resource.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(412, new HttpStatusCode(412, "Precondition Failed", "The server does not meet one of the preconditions that the requester put on the request.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(413, new HttpStatusCode(413, "Request Entity Too Large", "The request is larger than the server is willing or able to process.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(414, new HttpStatusCode(414, "Request-URI Too Long",
                "The URI provided was too long for the server to process. Often the result of too much data being encoded as a query-string of a GET request, in which case it should be converted to a POST request.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(415, new HttpStatusCode(415, "Unsupported Media Type",
                "The request entity has a media type which the server or resource does not support. For example, the client uploads an image as image/svg+xml, but the server requires that images use a different format.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(416, new HttpStatusCode(416, "Requested Range Not Satisfiable",
                "The client has asked for a portion of the file, but the server cannot supply that portion. For example, if the client asked for a part of the file that lies beyond the end of the file.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(417, new HttpStatusCode(417, "Expectation Failed", "The server cannot meet the requirements of the Expect request-header field.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(418, new HttpStatusCode(418, "I'm a teapot (RFC 2324)",
                "This code was defined in 1998 as one of the traditional IETF April Fools' jokes, in RFC 2324, Hyper Text Coffee Pot Control Protocol, and is not expected to be implemented by actual HTTP servers.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(419, new HttpStatusCode(419, "Authentication Timeout (not in RFC 2616)",
                "Not a part of the HTTP standard, 419 Authentication Timeout denotes that previously valid authentication has expired. It is used as an alternative to 401 Unauthorized in order to differentiate from otherwise authenticated clients being denied access to specific server resources.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(420, new HttpStatusCode(420, "Enhance Your Calm (Twitter)",
                "Not part of the HTTP standard, but returned by version 1 of the Twitter Search and Trends API when the client is being rate limited. Other services may wish to implement the 429 Too Many Requests response code instead.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(422, new HttpStatusCode(422, "Unprocessable Entity (WebDAV; RFC 4918)",
                "The request was well-formed but was unable to be followed due to semantic errors.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(423, new HttpStatusCode(423, "Locked (WebDAV; RFC 4918)", "The resource that is being accessed is locked.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(424, new HttpStatusCode(424, "Failed Dependency (WebDAV; RFC 4918)", "The request failed due to failure of a previous request (e.g., a PROPPATCH).",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(426, new HttpStatusCode(426, "Upgrade Required", "The client should switch to a different protocol such as TLS/1.0.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(428, new HttpStatusCode(428, "Precondition Required (RFC 6585)",
                "The origin server requires the request to be conditional. Intended to prevent \"the 'lost update' problem, where a client GETs a resource's state, modifies it, and PUTs it back to the server, when meanwhile a third party has modified the state on the server, leading to a conflict.\"",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(429, new HttpStatusCode(429, "Too Many Requests (RFC 6585)",
                "The user has sent too many requests in a given amount of time. Intended for use with rate limiting schemes.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(431, new HttpStatusCode(431, "Request Header Fields Too Large (RFC 6585)",
                "The server is unwilling to process the request because either an individual header field, or all the header fields collectively, are too large.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(440, new HttpStatusCode(440, "Login Timeout (Microsoft)", "A Microsoft extension. Indicates that your session has expired.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(444, new HttpStatusCode(444, "No Response (Nginx)",
                "Used in Nginx logs to indicate that the server has returned no information to the client and closed the connection (useful as a deterrent for malware).",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(449, new HttpStatusCode(449, "Retry With (Microsoft)",
                "A Microsoft extension. The request should be retried after performing the appropriate action. Often search-engines or custom applications will ignore required parameters. Where no default action is appropriate, the Aviongoo website sends a \"HTTP/1.1 449 Retry with valid parameters: param1, param2, . . .\" response. The applications may choose to learn, or not.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(450, new HttpStatusCode(450, "Blocked by Windows Parental Controls (Microsoft)",
                "A Microsoft extension. This error is given when Windows Parental Controls are turned on and are blocking access to the given webpage.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(451, new HttpStatusCode(451, "Redirect (Microsoft)",
                "Used in Exchange ActiveSync if there either is a more efficient server to use or the server cannot access the users' mailbox. The client is supposed to re-run the HTTP Autodiscovery protocol to find a better suited server.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(494, new HttpStatusCode(494, "Request Header Too Large (Nginx)",
                "Nginx internal code similar to 431 but it was introduced earlier in version 0.9.4 (on January the 21th, 2011).[original research?]", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(495, new HttpStatusCode(495, "Cert Error (Nginx)",
                "Nginx internal code used when SSL client certificate error occurred to distinguish it from 4XX in a log and an error page redirection.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(496, new HttpStatusCode(496, "No Cert (Nginx)",
                "Nginx internal code used when client didn't provide certificate to distinguish it from 4XX in a log and an error page redirection.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(497, new HttpStatusCode(497, "HTTP to HTTPS (Nginx)",
                "Nginx internal code used for the plain HTTP requests that are sent to HTTPS port to distinguish it from 4XX in a log and an error page redirection.",
                "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(498, new HttpStatusCode(498, "Token expired/invalid (Esri)",
                "Returned by ArcGIS for Server. A code of 498 indicates an expired or otherwise invalid token.", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(499, new HttpStatusCode(499, "Token required (Esri)",
                "Returned by ArcGIS for Server. A code of 499 indicates that a token is required (if no token was submitted).", "Client Error",
                "The 4xx class of status code is intended for cases in which the client seems to have errored. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and whether it is a temporary or permanent condition. These status codes are applicable to any request method. User agents should display any included entity to the user."));
        httpStatusCodes.put(500, new HttpStatusCode(500, "Internal Server Error",
                "A generic error message, given when an unexpected condition was encountered and no more specific message is suitable.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(501, new HttpStatusCode(501, "Not Implemented",
                "The server either does not recognize the request method, or it lacks the ability to fulfill the request. Usually this implies future availability (e.g., a new feature of a web-service API).",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(502, new HttpStatusCode(502, "Bad Gateway", "The server was acting as a gateway or proxy and received an invalid response from the upstream server.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(503, new HttpStatusCode(503, "Service Unavailable",
                "The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this is a temporary state.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(504, new HttpStatusCode(504, "Gateway Timeout",
                "The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(505, new HttpStatusCode(505, "HTTP Version Not Supported", "The server does not support the HTTP protocol version used in the request.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(506, new HttpStatusCode(506, "Variant Also Negotiates (RFC 2295)", "Transparent content negotiation for the request results in a circular reference.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(507, new HttpStatusCode(507, "Insufficient Storage (WebDAV; RFC 4918)",
                "The server is unable to store the representation needed to complete the request.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(508, new HttpStatusCode(508, "Loop Detected (WebDAV; RFC 5842)",
                "The server detected an infinite loop while processing the request (sent in lieu of 208 Already Reported).", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(509, new HttpStatusCode(509, "Bandwidth Limit Exceeded (Apache bw/limited extension)",
                "This status code is not specified in any RFCs. Its use is unknown.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(510, new HttpStatusCode(510, "Not Extended (RFC 2774)", "Further extensions to the request are required for the server to fulfill it.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(511, new HttpStatusCode(511, "Network Authentication Required (RFC 6585)",
                "The client needs to authenticate to gain network access. Intended for use by intercepting proxies used to control access to the network (e.g., \"captive portals\" used to require agreement to Terms of Service before granting full Internet access via a Wi-Fi hotspot).",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(520, new HttpStatusCode(520, "Origin Error (Cloudflare)",
                "This status code is not specified in any RFCs, but is used by Cloudflare's reverse proxies to signal an \"unknown connection issue between CloudFlare and the origin web server\" to a client in front of the proxy.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(521, new HttpStatusCode(521, "Web server is down (Cloudflare)",
                "This status code is not specified in any RFCs, but is used by Cloudflare's reverse proxies to indicate that the origin webserver refused the connection.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(522, new HttpStatusCode(522, "Connection timed out (Cloudflare)",
                "This status code is not specified in any RFCs, but is used by Cloudflare's reverse proxies to signal that a server connection timed out.", "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(523, new HttpStatusCode(523, "Proxy Declined Request (Cloudflare)",
                "This status code is not specified in any RFCs, but is used by Cloudflare's reverse proxies to signal a resource that has been blocked by the administrator of the website or proxy itself.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(524, new HttpStatusCode(524, "A timeout occurred (Cloudflare)",
                "This status code is not specified in any RFCs, but is used by Cloudflare's reverse proxies to signal a network read timeout behind the proxy to a client in front of the proxy.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(598, new HttpStatusCode(598, "Network read timeout error (Unknown)",
                "This status code is not specified in any RFCs, but is used by Microsoft HTTP proxies to signal a network read timeout behind the proxy to a client in front of the proxy.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));
        httpStatusCodes.put(599, new HttpStatusCode(599, "Network connect timeout error (Unknown)",
                "This status code is not specified in any RFCs, but is used by Microsoft HTTP proxies to signal a network connect timeout behind the proxy to a client in front of the proxy.",
                "Server Error",
                "The server failed to fulfill an apparently valid request. Response status codes beginning with the digit \"5\" indicate cases in which the server is aware that it has encountered an error or is otherwise incapable of performing the request. Except when responding to a HEAD request, the server should include an entity containing an explanation of the error situation, and indicate whether it is a temporary or permanent condition. Likewise, user agents should display any included entity to the user. These response codes are applicable to any request method."));

    }

    private int statusCode;

    private String status;

    private String desc;

    private String category;

    private String categoryDesc;

    private HttpStatusCode() {
        this(Integer.MIN_VALUE, null, null, null, null);
    }

    private HttpStatusCode(int statusCode, String status) {
        this(statusCode, status, null, null, null);
    }

    private HttpStatusCode(int statusCode, String status, String desc, String category, String categoryDesc) {
        this.statusCode = statusCode;
        this.status = status;
        this.desc = desc;
        this.category = category;
        this.categoryDesc = categoryDesc;
    }

    /**
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {

        HttpStatusCode sc = new HttpStatusCode();

        sc.setStatusCode(this.getStatusCode());
        sc.setStatus(this.getStatus());
        sc.setDesc(this.getDesc());
        sc.setCategory(this.category);
        sc.setCategoryDesc(this.getCategoryDesc());

        return sc;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HttpStatusCode other = (HttpStatusCode) obj;

        return statusCode == other.statusCode;
    }

    /**
     * 
     * @return the category
     * 
     * @since 2014. 6. 24.
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @return the categoryDesc
     * 
     * @since 2014. 6. 24.
     */
    public String getCategoryDesc() {
        return categoryDesc;
    }

    /**
     * 
     * @return the desc
     * 
     * @since 2014. 6. 24.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @return the status
     * 
     * @since 2014. 6. 24.
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @return the statusCode
     * 
     * @since 2014. 6. 24.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((categoryDesc == null) ? 0 : categoryDesc.hashCode());
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + statusCode;
        return result;
    }

    /**
     * @param category
     *            the category to set
     * 
     * @since 2014. 6. 24.
     */
    private void setCategory(String category) {
        this.category = category;
    }

    /**
     * @param categoryDesc
     *            the categoryDesc to set
     * 
     * @since 2014. 6. 24.
     */
    private void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    /**
     * @param desc
     *            the desc to set
     * 
     * @since 2014. 6. 24.
     */
    private void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param status
     *            the status to set
     * 
     * @since 2014. 6. 24.
     */
    private void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     * 
     * @since 2014. 6. 24.
     */
    private void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpStatusCodeEntity [statusCode=" + statusCode + ", status=" + status + ", desc=" + desc + ", category=" + category + ", categoryDesc=" + categoryDesc + "]";
    }

    public static HttpStatusCode code(int statusCode) {
        HttpStatusCode sc = httpStatusCodes.get(statusCode);

        try {
            return sc != null ? (HttpStatusCode) sc.clone() : null;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * <pre>
     * public static void main(String[] args) throws IOException {
     * 
     *     NamedTemplate template = new NamedTemplate(
     *             &quot;httpStatusCodes.put({httpStatusCode}, new HttpStatusCodeEntity({httpStatusCode}, \&quot;{status}\&quot;, \&quot;{desc}\&quot;, \&quot;{category}\&quot;, \&quot;{categoryDesc}\&quot;));&quot;);
     * 
     *     BufferedReader reader = IOUtils.getReader(HttpStatusCode.class.getResourceAsStream(&quot;HttpStatusCodeEng.txt&quot;), &quot;UTF-8&quot;);
     *     String readline = null;
     * 
     *     Pattern httpStatusCategoryPattern = Pattern.compile(&quot;(\\dxx)\\s(.+)&quot;);
     *     Pattern httpStatusCodePattern = Pattern.compile(&quot;(\\d\\d\\d)\\s(.+)&quot;);
     * 
     *     final int flagFlush = 0x00;
     *     final int flagCategory = 0x01;
     *     final int flagStatusCode = 0x02;
     * 
     *     int flag = flagFlush;
     * 
     *     Map&lt;Integer, CategoryInfo&gt; categories = new ConcurrentSkipListMap&lt;Integer, CategoryInfo&gt;();
     *     Map&lt;Integer, HttpStatusCodeEntity&gt; entities = new ConcurrentSkipListMap&lt;Integer, HttpStatusCodeEntity&gt;();
     * 
     *     HttpStatusCodeEntity entity = null;
     * 
     *     int category = -1;
     *     CategoryInfo categoryInfo = null;
     * 
     *     Matcher matcher = null;
     * 
     *     while ((readline = reader.readLine()) != null) {
     *         readline = readline.trim();
     * 
     *         // omit empty line
     *         if (readline.isEmpty()) {
     *             continue;
     *         }
     * 
     *         // check category
     *         matcher = httpStatusCategoryPattern.matcher(readline);
     * 
     *         if (matcher.matches()) {
     * 
     *             AssertUtils.assertFalse(flag == flagFlush);
     * 
     *             category = Integer.parseInt(matcher.group(1).substring(0, 1));
     *             categoryInfo = new CategoryInfo();
     *             categoryInfo.setStatus(matcher.group(2));
     * 
     *             categories.put(category, categoryInfo);
     * 
     *             flag = flagCategory;
     *         } else
     *         //
     *         if ((matcher = httpStatusCodePattern.matcher(readline)).matches()) {
     * 
     *             AssertUtils.assertFalse(flag == flagFlush);
     * 
     *             int codeCategory = Integer.parseInt(matcher.group(1).substring(0, 1));
     * 
     *             entity = new HttpStatusCodeEntity();
     *             entity.setStatusCode(Integer.parseInt(matcher.group(1)));
     *             entity.setStatus(matcher.group(2));
     * 
     *             CategoryInfo ci = categories.get(codeCategory);
     * 
     *             entity.setCategory(ci.getStatus());
     *             entity.setCategoryDesc(ci.getDesc());
     * 
     *             entities.put(entity.getStatusCode(), entity);
     * 
     *             flag = flagStatusCode;
     * 
     *         } else {
     *             switch (flag) {
     *                 case flagCategory:
     *                     categoryInfo.setDesc(readline);
     *                     break;
     *                 case flagStatusCode:
     *                     entity.setDesc(readline);
     *                     break;
     *                 default:
     *                     throw new IllegalArgumentException(&quot;Expected &quot; + flagCategory + &quot;, &quot; + flagStatusCode + &quot;. but flag is &quot; + flag);
     *             }
     * 
     *             flag = flagFlush;
     *         }
     *     }
     * 
     *     IOUtils.close(reader);
     * 
     *     // check result.
     *     for (HttpStatusCodeEntity code : entities.values()) {
     *         // &quot;httpStatusCodes.put({httpStatusCode}, new HttpStatusCodeEntity({httpStatusCode}, \&quot;{status}\&quot;,
     *         // \&quot;{desc}\&quot;, \&quot;{category}\&quot;, \&quot;{categoryDesc}\&quot;)&quot;);
     *         template.addValue(&quot;httpStatusCode&quot;, code.getStatusCode());
     *         template.addValue(&quot;status&quot;, code.getStatus());
     *         template.addValue(&quot;desc&quot;, code.getDesc());
     *         template.addValue(&quot;category&quot;, code.getCategory());
     *         template.addValue(&quot;categoryDesc&quot;, code.getCategoryDesc());
     * 
     *         System.out.println(template.format());
     *     }
     * 
     * }
     * 
     * static class CategoryInfo {
     *     private String status;
     *     private String desc;
     * 
     *     public String getStatus() {
     *         return status;
     *     }
     * 
     *     public void setStatus(String status) {
     *         this.status = status;
     *     }
     * 
     *     public String getDesc() {
     *         return desc;
     *     }
     * 
     *     public void setDesc(String desc) {
     *         this.desc = desc;
     *     }
     * 
     * }
     * </pre>
     */

}
