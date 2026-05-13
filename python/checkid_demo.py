import os
import sys

import requests


BASE_URL = "https://api.checkid.co.za"
SAMPLE_ID = "8903075555088"
SAMPLE_IDS = ["8903075555088", "9001015800081"]


def require_api_key():
    api_key = os.getenv("CHECKID_API_KEY")
    if not api_key:
        print("Set CHECKID_API_KEY before running this example.", file=sys.stderr)
        sys.exit(1)
    return api_key


def print_response(response):
    if response.status_code == 400:
        print("Bad request: check the ID number or JSON body.")
    elif response.status_code == 401:
        print("Unauthorised: check CHECKID_API_KEY.")
    elif response.ok:
        print("Success")
    else:
        print(f"Unexpected status: {response.status_code}")

    try:
        print(response.json())
    except ValueError:
        print(response.text)


def validate_single(session):
    response = session.get(f"{BASE_URL}/api/v1/validate/{SAMPLE_ID}", timeout=10)
    print("Single validation:")
    print_response(response)


def validate_bulk(session):
    response = session.post(
        f"{BASE_URL}/api/v1/validate/bulk",
        json={"idNumbers": SAMPLE_IDS},
        timeout=10,
    )
    print("\nBulk validation:")
    print_response(response)


def main():
    session = requests.Session()
    session.headers.update({"Authorization": f"Bearer {require_api_key()}"})

    validate_single(session)
    validate_bulk(session)


if __name__ == "__main__":
    main()
