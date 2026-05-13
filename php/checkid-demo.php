<?php

$baseUrl = 'https://api.checkid.co.za';
$sampleId = '8903075555083';
$sampleIds = ['8903075555083', '9001015800088'];

function requireApiKey(): string
{
    $apiKey = getenv('CHECKID_API_KEY');
    if (!$apiKey) {
        fwrite(STDERR, "Set CHECKID_API_KEY before running this example.\n");
        exit(1);
    }

    return $apiKey;
}

function request(string $method, string $url, string $apiKey, ?array $body = null): array
{
    $headers = ["Authorization: Bearer {$apiKey}"];
    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);

    if ($body !== null) {
        $headers[] = 'Content-Type: application/json';
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($body));
    }

    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    $responseBody = curl_exec($ch);
    $status = curl_getinfo($ch, CURLINFO_RESPONSE_CODE);

    if ($responseBody === false) {
        $responseBody = curl_error($ch);
    }

    curl_close($ch);

    return [$status, $responseBody];
}

function printResponse(int $status, string $body): void
{
    if ($status === 400) {
        echo "Bad request: check the ID number or JSON body.\n";
    } elseif ($status === 401) {
        echo "Unauthorised: check CHECKID_API_KEY.\n";
    } elseif ($status >= 200 && $status < 300) {
        echo "Success\n";
    } else {
        echo "Unexpected status: {$status}\n";
    }

    echo $body . "\n";
}

$apiKey = requireApiKey();

echo "Single validation:\n";
[$status, $body] = request('GET', "{$baseUrl}/api/v1/validate/{$sampleId}", $apiKey);
printResponse($status, $body);

echo "\nBulk validation:\n";
[$status, $body] = request('POST', "{$baseUrl}/api/v1/validate/bulk", $apiKey, ['idNumbers' => $sampleIds]);
printResponse($status, $body);
