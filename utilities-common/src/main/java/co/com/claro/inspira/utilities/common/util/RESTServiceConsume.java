package co.com.claro.inspira.utilities.common.util;

import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import co.com.claro.inspira.utilities.common.exception.dto.BusinessException;
import co.com.claro.inspira.utilities.common.exception.dto.EricssonFault;
import co.com.claro.inspira.utilities.common.exception.dto.ServiceConnectionException;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Map;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RESTServiceConsume {
	private static Client cliente;

	private static WebResource webResource;

	private static final Gson gson = new Gson();

	private static final String GET = "GET";

	private static final String POST = "POST";

	private static final String PUT = "PUT";

	private static Logger logger = LogManager.getLogger();

	public static <T> T callService(String url, String type, Map<String, String> headers,
			Map<String, String> queryParams, MultivaluedMap<String, String> formData, Object content,
			Class<T> returnClass)
			throws ServiceConnectionException, BusinessException, EricssonFault, BackendException, Exception {
		try {
			ClientResponse response = null;
			WebResource.Builder builder;
			cliente = Client.create();
			webResource = cliente.resource(url.trim());
			switch (type) {
			case "POST":
				if (queryParams != null && !queryParams.isEmpty())
					for (Map.Entry<String, String> entry : queryParams.entrySet()) {
						if (entry.getValue() != null)
							webResource = webResource.queryParam(entry.getKey(), entry.getValue());
					}
				builder = webResource.getRequestBuilder();
				for (Map.Entry<String, String> entry : headers.entrySet())
					builder = (WebResource.Builder) builder.header(entry.getKey(), entry.getValue());
				if (formData != null && !formData.isEmpty()) {
					ClientResponse clientResponse = (ClientResponse) webResource.post(ClientResponse.class, formData);
					break;
				}
				if (content != null) {
					ClientResponse clientResponse = (ClientResponse) builder.post(ClientResponse.class, content);
					break;
				}
				throw new ServiceConnectionException();
			case "GET":
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					if (entry.getValue() != null)
						webResource.header(entry.getKey(), entry.getValue());
				}
				if (queryParams != null && !queryParams.isEmpty())
					for (Map.Entry<String, String> entry : queryParams.entrySet()) {
						if (entry.getValue() != null)
							webResource = webResource.queryParam(entry.getKey(), entry.getValue());
					}
				response = (ClientResponse) webResource.get(ClientResponse.class);
				break;
			case "PUT":
				if (headers != null && !headers.isEmpty())
					for (Map.Entry<String, String> entry : headers.entrySet()) {
						if (entry.getValue() != null)
							webResource.header(entry.getKey(), entry.getValue());
					}
				if (queryParams != null && !queryParams.isEmpty())
					for (Map.Entry<String, String> entry : queryParams.entrySet()) {
						if (entry.getValue() != null)
							webResource = webResource.queryParam(entry.getKey(), entry.getValue());
					}
				if (formData != null && !formData.isEmpty()) {
					response = (ClientResponse) webResource.post(ClientResponse.class, formData);
					break;
				}
				if (content != null) {
					response = (ClientResponse) webResource.type("application/json").put(ClientResponse.class, content);
					break;
				}
				response = (ClientResponse) webResource.put(ClientResponse.class);
				break;
			default:
				throw new ServiceConnectionException("Solicitud no v" + type);
			}
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				String row = (String) response.getEntity(String.class);
				return (T) gson.fromJson(row, returnClass);
			}
			logger.info("Estatus del request " + response.getStatus());
			throw new BackendException(String.valueOf(response.getStatus()),
					"Error consumo del servicio legado. Cod Status: " + response.getStatus());
		} catch (ClientHandlerException e) {
			throw new Exception("Error del servicio legado - ClientHandlerException: ");
		} catch (ServiceConnectionException | com.sun.jersey.api.client.UniformInterfaceException e) {
			throw new ServiceConnectionException("Error en el consumo del servicio", e);
		}
	}
}
