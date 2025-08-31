from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def test_root():
    res = client.get("/")
    assert res.staus_code == 200
    assert res.json()["status"] == "ok"


def test_sentiment():
    res = client.post("/sentiment", json={"text": "I love this"})
    assert res.staus_code == 200
    assert res.json()["sentiment_score"] == 1.0
