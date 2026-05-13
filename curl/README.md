# cURL CheckID API Example

Raw cURL examples for the CheckID South African ID verification API.

## Setup

```bash
export CHECKID_API_KEY="YOUR_API_KEY"
```

## Single Validation

```bash
curl -s "https://api.checkid.co.za/api/v1/validate/8903075555088" \
  -H "Authorization: Bearer ${CHECKID_API_KEY}"
```

## Bulk Validation

```bash
curl -s "https://api.checkid.co.za/api/v1/validate/bulk" \
  -H "Authorization: Bearer ${CHECKID_API_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"idNumbers":["8903075555088","9001015800081"]}'
```

## Error Handling

The API returns `400 Bad Request` for invalid ID formats or request bodies and `401 Unauthorized` for missing or invalid API keys. Add `-i` to the examples above if you want to inspect response headers and status codes.
