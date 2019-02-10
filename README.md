## Read [Project Documentation](https://github.com/MovieTrender/Documentation "Project Documentation")


## Mahout Sequence Generator


Generates a sequence file for training Mahout.

The output of Mahout is a [Sentiment Model](https://github.com/MovieTrender/SentimentModel "Sentiment Model") for classifying tweets.

### Use

The process is executed as follows:

	hadoop jar mahoutSequenceGenerator.jar "Class to assign" <<InputFolder (local folder)>> <<OutputFolder (in HDFS)>>

### What it does?

Generates a sequence file with all the individual files downloaded from Twitter.

Mahout Naive Bayes only accepts files in a specific format:

- Key: Class/ FileID
- Value: Text

this process takes all the files in a folder (iterating recursively) and generates a single sequence file with the key as KEY and the content of the file as VALUE.






