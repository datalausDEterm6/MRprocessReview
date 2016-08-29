
# MRwithReview
mr project with review data

##command to run this mr 

hadoop fs -put /home/cloudera/Desktop/yelp_training_set_review.json cloudera/project/yelp/review/input/2

mvn clean package && hadoop jar target/YelpProject-0.0.1-SNAPSHOT.jar MapReduce.Demo cloudera/project/yelp/review/input/2 cloudera/project/yelp/review/output/5


