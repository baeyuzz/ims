from konlpy.tag import Okt
import pandas as pd
import numpy as np
from sklearn.pipeline import Pipeline
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.multiclass import OneVsRestClassifier
import joblib
import pickle


globall = pd.read_csv('./csv/global.csv', encoding="utf-8", error_bad_lines=False)
challenge = pd.read_csv('./csv/challenge.csv', encoding="utf-8", error_bad_lines=False)
sincerity = pd.read_csv('./csv/sincerity.csv', encoding="utf-8", error_bad_lines=False)
communication = pd.read_csv('./csv/communication.csv', encoding="utf-8", error_bad_lines=False)
responsibility = pd.read_csv('./csv/responsibility.csv', encoding="utf-8", error_bad_lines=False)
creativity = pd.read_csv('./csv/creativity.csv', encoding="utf-8", error_bad_lines=False)
goal = pd.read_csv('./csv/goal.csv', encoding="utf-8", error_bad_lines=False)
honest = pd.read_csv('./csv/honest.csv', encoding="utf-8", error_bad_lines=False)
stopwords = pd.read_csv('./csv/stopwords.csv')


def tokenizer(raw, pos=["Noun", "Verb"]):
    return [
        word for word, tag in okt.pos(
            raw,
            norm=True,  # normalize 정제 과정
            stem=True  # stemming 정제 과정
        )
        if len(word) > 1 and tag in pos and word not in stopwords
    ]


okt = Okt()

keyword_names = ['글로벌', '도전', '성실', '의사소통', '성취지향', '주인의식', '창의', '정직']

all_data = globall.append(challenge).append(sincerity).append(communication).append(goal) \
    .append(responsibility).append(creativity).append(honest)

from sklearn.model_selection import train_test_split

train, test = train_test_split(all_data, test_size=0.3, random_state=40)

print(train.head())
print(test.head())


vectorize = TfidfVectorizer(
    ngram_range=(1, 2),
    max_df=0.80,
    min_df=0,
    sublinear_tf=True
)

tdm = vectorize.fit_transform(train["sentence"].apply(lambda x: np.str_(x)))

############## SVM-LinearSVC
from sklearn.svm import LinearSVC

SVC_pipeline = Pipeline([
    ('tfidf', vectorize),
    ('clf', OneVsRestClassifier(LinearSVC(), n_jobs=1)),
])
text_clf_svc = SVC_pipeline.fit(train["sentence"].apply(lambda x: np.str_(x)), train["label"])


joblib.dump(text_clf_svc, './model/svcModel.pkl')




# ############## svm
# from sklearn.linear_model import SGDClassifier
#
# text_clf_svm = Pipeline([('vect', vectorize),
#                          ('tfidf', TfidfTransformer()),
#                          ('clf-svm', SGDClassifier(loss='hinge', penalty='l2', alpha=1e-3, max_iter=1000,
#                                                    random_state=42))])  # 손실함수 기댓값 최소화
# text_clf_svm = text_clf_svm.fit(train["sentence"].apply(lambda x: np.str_(x)), train["label"])
#
# ############## Naive Bayes
# from sklearn.naive_bayes import MultinomialNB
#
# NB_pipeline = Pipeline([
#     ('tfidf', vectorize),
#     ('clf', OneVsRestClassifier(MultinomialNB(
#         fit_prior=True, class_prior=None))),
# ])
# text_clf_nb = NB_pipeline.fit(train["sentence"].apply(lambda x: np.str_(x)), train["label"])
#
#
# ############## Logistic Regression
# from sklearn.linear_model import LogisticRegression
#
# LogReg_pipeline = Pipeline([
#     ('tfidf', vectorize),
#     ('clf', OneVsRestClassifier(LogisticRegression(solver='sag'), n_jobs=1)),
# ])
# text_clf_logreg = LogReg_pipeline.fit(train["sentence"].apply(lambda x: np.str_(x)), train["label"])
#
# # svm
# print("train", np.mean(text_clf_svm.predict(train["sentence"]) == train["label"]))
# print("test", np.mean(text_clf_svm.predict(test["sentence"]) == test["label"]))
#
#
# # Naive Bayes
# print("train", np.mean(text_clf_nb.predict(train["sentence"]) == train["label"]))
# print("test", np.mean(text_clf_nb.predict(test["sentence"]) == test["label"]))
#
#
# # SVM-LinearSVC
# print("train", np.mean(text_clf_svc.predict(train["sentence"]) == train["label"]))
# print("test", np.mean(text_clf_svc.predict(test["sentence"]) == test["label"]))
#
#
# # Logistic Regression
# print("train", np.mean(text_clf_logreg.predict(train["sentence"]) == train["label"]))
# print("test", np.mean(text_clf_logreg.predict(test["sentence"]) == test["label"]))
#
# def Predict(model, text):
#     result = model.predict([text])
#     print(keyword_names[result[0] - 1])
#
# Predict(text_clf_svm,
#         "맡은 일에 대한 열정과 책임을 바탕으로 새로운 환경과 새로운 업무에 잘 적응 할 수 있는 능력이 있습니다. 제자리에 머물지 않고 나아가는 인재가 되어 LG CNS의 성장에 기여하겠습니다.")
# Predict(text_clf_svm,
#         "새로운 것을 만들어낸 경험이 있습니다"
#         )
# print()
#
# Predict(text_clf_nb,
#         "팀원들과 협업을 통해 문제를 해결했습니다. 매일 회의를 했습니다."
#         )
# Predict(text_clf_nb,
#         "새로운 것을 만들어낸 경험이 있습니다"
#         )
# print()
#
# Predict(text_clf_svc,
#         "맡은 일에 대한 열정과 책임을 바탕으로 새로운 환경과 새로운 업무에 잘 적응 할 수 있는 능력이 있습니다. 제자리에 머물지 않고 나아가는 인재가 되어 LG CNS의 성장에 기여하겠습니다.")
# Predict(text_clf_svc,
#         "팀원들과 협업을 통해 문제를 해결했습니다. 매일 회의를 했습니다."
#         )
# print()
#
# Predict(text_clf_logreg,
#         "맡은 일에 대한 열정과 책임을 바탕으로 새로운 환경과 새로운 업무에 잘 적응 할 수 있는 능력이 있습니다. 제자리에 머물지 않고 나아가는 인재가 되어 LG CNS의 성장에 기여하겠습니다.")
# Predict(text_clf_logreg,
#         "새로운 것을 만들어낸 경험이 있습니다"
#         )
#
#
# joblib.dump(text_clf_svm, './model/SVM.joblib')
# joblib.dump(text_clf_nb, './model/NB.joblib')
# joblib.dump(text_clf_svc, './model/SVC.joblib')
# joblib.dump(text_clf_logreg, './model/LogReg.joblib')
#
# joblib.dump(text_clf_svm, './model/SVMmodel.pkl')
#
# joblib.dump(text_clf_nb, './model/nbModel.pkl')
#
# joblib.dump(text_clf_logreg, './model/logisticModel.pkl')