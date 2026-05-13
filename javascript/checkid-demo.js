const BASE_URL = "https://api.checkid.co.za";
const SAMPLE_ID = "8903075555088";
const SAMPLE_IDS = ["8903075555088", "9001015800081"];

function requireApiKey() {
  const apiKey = process.env.CHECKID_API_KEY;
  if (!apiKey) {
    console.error("Set CHECKID_API_KEY before running this example.");
    process.exit(1);
  }
  return apiKey;
}

async function printResponse(response) {
  if (response.status === 400) {
    console.log("Bad request: check the ID number or JSON body.");
  } else if (response.status === 401) {
    console.log("Unauthorised: check CHECKID_API_KEY.");
  } else if (response.ok) {
    console.log("Success");
  } else {
    console.log(`Unexpected status: ${response.status}`);
  }

  const text = await response.text();
  try {
    console.log(JSON.parse(text));
  } catch {
    console.log(text);
  }
}

async function validateSingle(headers) {
  const response = await fetch(`${BASE_URL}/api/v1/validate/${SAMPLE_ID}`, { headers });
  console.log("Single validation:");
  await printResponse(response);
}

async function validateBulk(headers) {
  const response = await fetch(`${BASE_URL}/api/v1/validate/bulk`, {
    method: "POST",
    headers: { ...headers, "Content-Type": "application/json" },
    body: JSON.stringify({ idNumbers: SAMPLE_IDS }),
  });
  console.log("\nBulk validation:");
  await printResponse(response);
}

async function main() {
  const headers = { Authorization: `Bearer ${requireApiKey()}` };
  await validateSingle(headers);
  await validateBulk(headers);
}

main().catch((error) => {
  console.error(error.message);
  process.exit(1);
});
