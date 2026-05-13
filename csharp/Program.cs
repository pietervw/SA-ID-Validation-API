using System.Net;
using System.Net.Http.Headers;
using System.Text;

const string baseUrl = "https://api.checkid.co.za";
const string sampleId = "8903075555088";
var sampleIdsJson = "{\"idNumbers\":[\"8903075555088\",\"9001015800081\"]}";

var apiKey = Environment.GetEnvironmentVariable("CHECKID_API_KEY");
if (string.IsNullOrWhiteSpace(apiKey))
{
    Console.Error.WriteLine("Set CHECKID_API_KEY before running this example.");
    Environment.Exit(1);
}

using var client = new HttpClient();
client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", apiKey);

Console.WriteLine("Single validation:");
var singleResponse = await client.GetAsync($"{baseUrl}/api/v1/validate/{sampleId}");
await PrintResponse(singleResponse);

Console.WriteLine("\nBulk validation:");
using var body = new StringContent(sampleIdsJson, Encoding.UTF8, "application/json");
var bulkResponse = await client.PostAsync($"{baseUrl}/api/v1/validate/bulk", body);
await PrintResponse(bulkResponse);

static async Task PrintResponse(HttpResponseMessage response)
{
    if (response.StatusCode == HttpStatusCode.BadRequest)
    {
        Console.WriteLine("Bad request: check the ID number or JSON body.");
    }
    else if (response.StatusCode == HttpStatusCode.Unauthorized)
    {
        Console.WriteLine("Unauthorised: check CHECKID_API_KEY.");
    }
    else if (response.IsSuccessStatusCode)
    {
        Console.WriteLine("Success");
    }
    else
    {
        Console.WriteLine($"Unexpected status: {(int)response.StatusCode}");
    }

    Console.WriteLine(await response.Content.ReadAsStringAsync());
}
