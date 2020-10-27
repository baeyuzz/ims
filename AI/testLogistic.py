# -*- coding: utf-8 -*-

import joblib

from sklearn.feature_extraction.text import TfidfVectorizer
from konlpy.tag import Okt


def tokenizer(raw, pos=["Noun", "Verb"]):
    return [
        word for word, tag in okt.pos(
            raw,
            norm=True,  # normalize 정제 과정
            stem=True  # stemming 정제 과정
        )
        # if len(word) > 1 and tag in pos and word not in stopword
    ]


okt = Okt()

category = ['글로벌', '도전', '성실', '의사소통', '성취지향', '주인의식', '창의', '정직']

vectorize = TfidfVectorizer(
    ngram_range=(1, 3),  # n-gram 3
    tokenizer=tokenizer,
    max_df=0.95,
    min_df=0,
    sublinear_tf=True
)

clf_from_joblib = joblib.load('./model/logisticModel.pkl')


print(1 + clf_from_joblib.decision_function(
    ["뉴질랜드 오타고 대학에서 어학연수를 1달 다녀왔습니다. 여러 국가에서 온 친구들을 만났습니다. 다양한 문화를 경험했습니다."]),
      clf_from_joblib.predict(["뉴질랜드 오타고 대학에서 어학연수를 1달 다녀왔습니다. 영어를 배우며 다양한 국가에서 온 친구들을 만났습니다. 다양한 문화를 경험했습니다."]))

print(1 + clf_from_joblib.decision_function(
    [
        "팀원들과 회의를 하고 의견을 나눴습니다."
    ]),
      clf_from_joblib.predict([
          "팀원들과 회의를 하고 의견을 나눴습니다."
      ]))

print(1 + clf_from_joblib.decision_function(
    [
        "새로운 의견과 방법을 찾았습니다."
    ]),
      clf_from_joblib.predict([
          "새로운 의견과 방법을 찾았습니다."
      ]))

print(1 + clf_from_joblib.decision_function(
    [
        "목표를 세웠고 달성할 수 있었습니다."
    ]),
      clf_from_joblib.predict([
          "목표를 세웠고 달성할 수 있었습니다."
      ]))