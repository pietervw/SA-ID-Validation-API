# South African ID Validation API Examples

[![SA ID validation by CheckID.co.za](https://img.shields.io/badge/SA_ID_validation-CheckID.co.za-0b7a3e)](https://checkid.co.za)

Runnable examples for the [CheckID.co.za](https://checkid.co.za) South African ID validation API in C#, Python, PHP, Java, JavaScript, and cURL. Use them to validate SA ID numbers one at a time or in bulk from your own application.

**What the API checks:** CheckID performs **structural validation, not a Home Affairs lookup**. It checks that a 13-digit South African ID number has a valid format, a real date of birth, a gender sequence, a citizenship indicator, and a correct Luhn checksum. It does not confirm that the ID number belongs to a real person, and it does not query any government database.

CheckID is built for POPIA-conscious teams: ID numbers sent to the API are validated and not stored.

## Links

- CheckID homepage: [https://checkid.co.za](https://checkid.co.za)
- Developer guide: [https://checkid.co.za/api-documentation/developers-guide](https://checkid.co.za/api-documentation/developers-guide)
- Pricing and API access: [https://checkid.co.za/pricing](https://checkid.co.za/pricing)
- API reference (Swagger): [https://api.checkid.co.za/index.html](https://api.checkid.co.za/index.html)

## Contents

- [Quick start](#quick-start)
- [Language examples](#language-examples)
- [API endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Error handling](#error-handling)
- [Sample ID numbers](#sample-id-numbers)
- [Resources](#resources)
- [Link to us](#link-to-us)

## Quick start

Set your API key and run a single validation request:

```bash
export CHECKID_API_KEY="YOUR_API_KEY"
curl -s "https://api.checkid.co.za/api/v1/validate/8903075555083" \
  -H "Authorization: Bearer ${CHECKID_API_KEY}"
```

Need a key? Create an account at [CheckID.co.za](https://checkid.co.za), then see the [CheckID pricing page](https://checkid.co.za/pricing) for API access options.

## Language examples

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

For a step-by-step walkthrough, read the [CheckID developer guide](https://checkid.co.za/api-documentation/developers-guide).

## API endpoints

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

{ "idNumbers": ["8903075555083", "9001015800088"] }
```

Example successful response:

```json
{
  "idNumber": "8903075555083",
  "isValid": true,
  "dob": "1989-03-07T00:00:00",
  "age": 37,
  "gender": "M",
  "citizenship": "SA Citizen"
}
```

`age` is calculated on the day of the request. Bulk validation returns an array of response objects.

The full request and response schema is in the [CheckID API reference](https://api.checkid.co.za/index.html).

## Authentication

All requests use bearer token authentication:

```http
Authorization: Bearer YOUR_API_KEY
```

Do not hardcode API keys in source control. These examples read the key from `CHECKID_API_KEY`.

## Error handling

| Status | Meaning |
|---|---|
| `400 Bad Request` | Invalid ID number format or request body |
| `401 Unauthorized` | Missing or invalid API key |

The sample code prints a clear message for each case and includes the response body to help with debugging.

## Sample ID numbers

The ID numbers in this repository (`8903075555083`, `9001015800088`) are synthetic test values. They pass the structural and Luhn checksum rules but are not taken from real people. Never commit real people's ID numbers to code, tests, or issues. For more edge cases, see the [SA ID validator test cases](https://checkid.co.za/sa-id-validator-test-cases).

## Resources

- [CheckID.co.za homepage](https://checkid.co.za)
- [CheckID developer guide](https://checkid.co.za/api-documentation/developers-guide)
- [CheckID pricing and API access](https://checkid.co.za/pricing)
- [CheckID API reference](https://api.checkid.co.za/index.html)
- [5-minute integration tutorial](https://checkid.co.za/articles/5-minute-id-verification-setup)
- [Bulk SA ID validation](https://checkid.co.za/bulk-id-validation)

## Link to us

If these examples helped you, you can add a CheckID badge to your README or website.

Preview: [![SA ID validation by CheckID.co.za](https://img.shields.io/badge/SA_ID_validation-CheckID.co.za-0b7a3e)](https://checkid.co.za)

Markdown:

```markdown
[![SA ID validation by CheckID.co.za](https://img.shields.io/badge/SA_ID_validation-CheckID.co.za-0b7a3e)](https://checkid.co.za)
```

HTML:

```html
<a href="https://checkid.co.za"><img src="https://img.shields.io/badge/SA_ID_validation-CheckID.co.za-0b7a3e" alt="SA ID validation by CheckID.co.za"></a>
```

Plain text link:

```html
<a href="https://checkid.co.za">CheckID.co.za: South African ID number validation</a>
```
