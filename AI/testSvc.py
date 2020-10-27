# -*- coding: utf-8 -*-

import joblib
from sklearn.feature_extraction.text import TfidfVectorizer
from konlpy.tag import Okt
import pandas as pd
import numpy as np


class testSVC:
    def test(self):

        content = []

        content.append(self.content)

        stopwords = pd.read_csv('./csv/stopwords.csv')

        def tokenizer(raw, pos=["Noun", "Verb"]):
            return [
                word for word, tag in okt.pos(
                    raw,
                    norm=True,
                    stem=True
                )
                if len(word) > 1 and tag in pos and word not in stopwords
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

        clf_from_joblib = joblib.load('./model/svcModel.pkl')

        print()
        output = clf_from_joblib.decision_function(content)

        probs = np.exp(output) / np.sum(np.exp(output)) * 100

        print(probs)

    def __init__(self, content):
        self.content = content
        self.test()


testSVC("저는 정직합니다 청렴하고 아주 깨끗합니다")
