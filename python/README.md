# Python CheckID API Example

Runnable Python 3.8+ example using `requests`.

## Setup

```bash
python3 -m venv .venv
source .venv/bin/activate
pip install requests
export CHECKID_API_KEY="YOUR_API_KEY"
```

## Run

```bash
python checkid_demo.py
```

The script demonstrates single validation, bulk validation, and basic `400`/`401` handling.
