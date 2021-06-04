# Java code embeddings from compiled class files for code similarity tasks

### Summary

A novel and simple approach for generating source code embeddings for code similarity tasks.

This *compiler-in-the-loop* approach works by compiling the high level source code to a typed intermediate language. Here we demonstrate for Java using the JVM instruction set. For other languages such as C/C++, LLVM intermediate language could be used.

We take the instruction sequence in each method and generate a set of features.

* Function calls are abstracted using the parameter and return types and attached to invoke instructions.
* Class name is attached to the 'new' instruction.
* Parameter and return types from function definition are currently not used since they're not part of the instruction stream.

Features:

* Each instruction is a unigram feature.
* We may take every k-subsequence in the instruction sequence to generate a k-subsequence feature.
  * Currently we generate for binary subsequences and generate 2-subsequence features.

### n-subsequence embeddings

In this approach, embeddings for n-subsequences (of instruction sequences) are learnt using a Word2Vec-style skip-gram model. Currently only binary subsequences are used. Method embeddings are generated by summing up the embeddings of n-subsequences contained in it.

Method similarity checking is done by computing vector similarity of method embeddings.

### TF-IDF embeddings

In this approach, during the learning phase, the IDF values for the features are learnt and stored in a JSON file.

During similarity checking, the TF vectors are generated and scaled using the previously learnt IDF values. Cosine similarity is used as the similarity measure.


### Pre-requisites

* A recent version of Python 3.
* A recent version of JDK (javap is used to generate JVM disassembly) - must be in the path.
* scikit-learn: pip install scikit-learn
* pytorch: pip install torch (not required for TF-IDF embeddings)

### Running (n-subsequence embeddings)

#### Embedding generation

```console
python compute_nsubseqs.py <folder containing class files> <subseq output path>
cd word2vec
python trainer.py <subseq output path> <vec output file path>
```

#### Similarity checking

```console
python compute_nsubseq_emb_similarity.py <folder containing class files> <vec file path>
```


#### IDF generation:

```console
python compute_idf.py <folder containing class files> <IDF output path>
```

The folder containing class files is recursively searched for class files and the IDF is computed by aggregating data from all methods in all the class files.

#### Similarity checking

```console
python compute_tf_idf_similarity.py <folder containing class files> <IDF path>
```

To run against the test files using the pretrained vectors from commons-lang library:
```console
cd test
javac *.java
cd ..
python compute_nsubseq_emb_similarity test test/commons_lang_ngrams.vec
```

### Running (TF-IDF embeddings)

#### IDF generation:

```console
python compute_idf.py <folder containing class files> <IDF output path>
```

The folder containing class files is recursively searched for class files and the IDF is computed by aggregating data from all methods in all the class files.

#### Similarity checking

```console
python compute_tf_idfsimilarity.py <folder containing class files> <IDF path>
```

The IDF path must point to a previously computed IDF file. All the class files are read and pair-wise similarity of all methods are printed.

To run against the test files:
```console
cd test
javac *.java
cd ..
python compute_tf_idf_similarity test test/idf_commons_lang.json
```
### Pre-computed

* The file test/idf_commons_lang.json contains IDF computed from all the class files in the Apache Commons Lang library.
* The file test/commons_lang_ngrams.vec contains unary and binary-subsequence embeddings trained from all the class files in Apache Commongs Lang library.

### Citing

If you are using or extending this work as part of your research, please cite as:

Poroor, Jayaraj, "Java code embeddings from compiled class files for code similarity tasks", (2021), GitHub repository, https://github.com/jayarajporoor/code_embedding

BibTex:

    @misc{Poroor2021,
       author = {Poroor, Jayaraj},
       title = {Java code embeddings from compiled class files for code similarity tasks},
       year = {2021},
       publisher = {GitHub},
       journal = {GitHub repository},
       howpublished = {\url{https://github.com/jayarajporoor/code_embedding}}
    }

### Related work

A few deep learning models have been proposed in recent years to generate source code embeddings:

* Code2Vec - https://github.com/tech-srl/code2vec
* CodeBERT - https://github.com/microsoft/CodeBERT
