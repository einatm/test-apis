import com.fasterxml.jackson.databind.JsonNode;
import com.liveperson.api.infra.GeneralAPI;
import com.liveperson.api.infra.ServiceName;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;

/**
 * @author einatm
 * @date 6/27/17
 */
public class SmtTest {

    private static final String LP_ACCOUNT = "61326154";

    @ServiceName("smt")
    public interface Smt {
        @GET("/api/js/{account}")
        Call<JsonNode> baseURI(@Path("account") String account, @Query("t") String requestType, @Query("r") String r,
                               @Query("pid") String pid, @Query("ts") String ts,
                               @Query("u") String u,  @Query("cb") String cb, @Query("vid") String vid);
    }

    @Test
    public void hello() throws Exception {
        Map<String, String> domains = GeneralAPI.getDomains("https://adminlogin.liveperson.net", LP_ACCOUNT);
        final JsonNode body = GeneralAPI.apiEndpoint(domains, Smt.class).baseURI(LP_ACCOUNT, "sp", "http://google.com/",
                "153523", "12", "http://www.com", "true", "4232").execute().body();
        System.out.println(body);
    }
}