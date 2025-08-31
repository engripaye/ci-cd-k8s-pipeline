from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI(title="Python Service")


class Item(BaseModel):
    text: str


@app.get("/")
async def root():
    return {
        "status": "ok", "service": "python-service"
    }


@app.post("/sentiment")
async def sentiment(item: Item):
    # placeholder: real model integration would goo here
    text = item.text.lower()
    score = 1.0 if "good" in text or "love" in text else 0.0
    return {"text": item.text, "sentiment_score": score}
