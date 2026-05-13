# South African ID Verification API Demo

Runnable examples for the CheckID South African ID verification API in C#, Python, PHP, Java, JavaScript, and cURL. Use these samples to validate SA ID numbers, test bulk ID validation, and integrate structural South African ID number checks into your application.

CheckID.co.za provides a fast, POPIA-aware API for validating whether a South African ID number is structurally valid. It checks format, date encoding, gender sequence, citizenship indicator, and checksum. It does not verify a person's identity against a government database.

## Contents

- [Quick Start](#quick-start)
- [Language Examples](#language-examples)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Resources](#resources)

## Quick Start

Set your API key and run a single validation request:

```bash
export CHECKID_API_KEY="YOUR_API_KEY"
curl -s "https://api.checkid.co.za/api/v1/validate/8903075555088" \
  -H "Authorization: Bearer ${CHECKID_API_KEY}"
```

Need a key? Sign up at [CheckID.co.za](https://checkid.co.za) and view the [pricing page](https://checkid.co.za/pricing) for API access options.

## Language Examples

| Language | Directory | Runtime |
|---|---|---|
| C# / .NET | [`csharp/`](csharp/) | .NET 6+ |
| Python | [`python/`](python/) | Python 3.8+ with `requests` |
| PHP | [`php/`](php/) | PHP 8.0+ with cURL |
| Java | [`java/`](java/) | Java 11+ |
| JavaScript / Node.js | [`javascript/`](javascript/) | Node.js 18+ |
| cURL | [`curl/`](curl/) | cURL |

Each example demonstrates:

- Single ID validation with `GET /api/v1/validate/{idNumber}`
- Bulk ID validation with `POST /api/v1/validate/bulk`
- API key loading from `CHECKID_API_KEY`
- Basic handling for `400 Bad Request` and `401 Unauthorized`

## API Endpoints

Base URL:

```text
https://api.checkid.co.za
```

Single validation:

```http
GET /api/v1/validate/{idNumber}
Authorization: Bearer YOUR_API_KEY
```

Bulk validation:

```http
POST /api/v1/validate/bulk
Authorization: Bearer YOUR_API_KEY
Content-Type: application/json

{ "idNumbers": ["8903075555088", "9001015800081"] }
```

Example successful response:

```json
{
  "idNumber": "8903075555088",
  "isValid": true,
  "dob": "1989-03-07T00:00:00",
  "age": 35,
  "gender": "M",
  "citizenship": "SA Citizen"
}
```

Bulk validation returns an array of response objects.

## Authentication

All requests use bearer token authentication:

```http
Authorization: Bearer YOUR_API_KEY
```

Do not hardcode API keys in source control. These examples read the key from `CHECKID_API_KEY`.

## Error Handling

| Status | Meaning |
|---|---|
| `400 Bad Request` | Invalid ID number format or request body |
| `401 Unauthorized` | Missing or invalid API key |

The sample code prints clear messages for these cases and includes the response body to help with debugging.

## Resources

- [CheckID.co.za homepage](https://checkid.co.za)
- [Pricing and API access](https://checkid.co.za/pricing)
- [API documentation](https://api.checkid.co.za/index.html)
- [5-minute integration tutorial](https://checkid.co.za/articles/5-minute-id-verification-setup)
- [Developer guide](https://checkid.co.za/api-documentation/developers-guide)

## Repository Topics

Suggested GitHub topics: `south-african-id`, `id-verification`, `api-demo`, `sa-id-number`, `id-validation`.
