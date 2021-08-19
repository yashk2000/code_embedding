import argparse
import numpy as np
import gensim
from jvm_embedding import *
from sklearn.decomposition import TruncatedSVD

scale = 1.0
def similarity(v1, v2):
    #return np.dot(v1, v2)
    return np.linalg.norm(v1 - v2)/scale

def main(model_path, n_components, inpath):
    model = gensim.models.KeyedVectors.load_word2vec_format(model_path, binary=False)
    wv = model.vectors
    
    tsvd = TruncatedSVD(n_components=n_components)
    mean = np.average(wv, axis=0)
    tsvd.fit(wv - mean)
    components = np.matmul(np.matmul(wv, tsvd.components_.T), tsvd.components_)
    processed = wv - mean - components

    model.vectors = processed
    tf_dataset, df, n_methods = build_tf_df(inpath)

    method_embeddings = {}
    for path, method_features in tf_dataset.items():
        method_embeddings[path] = {}
        for  method in method_features.keys():
            feature_dict = method_features[method]
            method_embeddings[path][method] = sum(model[f]*v for f, v in feature_dict.items() if f in model)

    for path1, method_features1 in tf_dataset.items():
        for method1, feature_dict1 in method_features1.items():
            print()
            print(path1, "@", method1, "vs: ")
            res = []
            for path2, method_features2 in tf_dataset.items():
                    for method2, feature_dict2 in method_features2.items():
                        if path1 == path2 and method1 == method2:
                            continue
                        sim = similarity(method_embeddings[path1][method1], method_embeddings[path2][method2])
                        res.append( (path2 + " " + method2, sim))
            res.sort(reverse=False, key=lambda t: t[1])
            for target, sim in res:
                print("    ", target, ":", sim)

def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('-m', '--model_path', nargs='*', help='path of model(s)')
    parser.add_argument('-i', '--test_path', nargs='*', help='path of test data')
    parser.add_argument('-n', '--n_components', type=int, default=5, help='number of dimensions postprocessing')

    args = parser.parse_args()
    for model_path in args.model_path:
        main(model_path, args.n_components, args.test_path[0])


if __name__ == '__main__':
    get_args()