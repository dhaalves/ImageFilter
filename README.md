#Image Classification Filter for WEKA

##Preparing the datasets

Datasets (in ARFF format) should contain a minimum of two attributes: a string (which stores the filenames of the images) and a class.
Additional attributes may also be in the dataset and they will be copied unchanged by the filter.

For example, this is dogs.arff from the data folder:
````
@relation dogs
@attribute filename string
@attribute class {BEAGLE,GERMAN_SHORTHAIRED}
@data
img_beagle_1.jpg,BEAGLE
img_beagle_10.jpg,BEAGLE
img_beagle_12.jpg,BEAGLE
img_beagle_13.jpg,BEAGLE
img_beagle_14.jpg,BEAGLE
img_beagle_15.jpg,BEAGLE
img_german_shorthaired_42.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_43.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_44.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_45.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_46.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_47.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_48.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_49.jpg,GERMAN_SHORTHAIRED

````

##Using the filters from the command line
Run the image filter, specifying your ARFF file with the image filenames and the class index as the input. The output should be the name of the file where you want the images stored, e.g.:
````
java -cp classpath_to_weka weka.filters.unsupervised.instance.imagefilter.BasicImageFeatures -i dogs.arff -o dogs_features.arff

````

Note: In dogs.arff above, filenames are *not* fully qualified.
Therefore you should change working directory to the directory containing the images before executing the above command.
Alternatively, it is possible to replace each filename with its fully qualified name and run the filtering command from anywhere.

The resulting file dogs_features.arff should look like this:
````
@relation dogs-weka.filters.unsupervised.instance.imagefilter.BasicImageFeatures

@attribute filename string
@attribute B0 numeric
@attribute B1 numeric
@attribute B2 numeric
@attribute B3 numeric
@attribute B4 numeric
@attribute B5 numeric
@attribute B6 numeric
@attribute B7 numeric
@attribute class {BEAGLE,GERMAN_SHORTHAIRED}

@data

img_beagle_1.jpg,0.643343,0.001476,0.454161,0.2,0.423987,0.030935,-1.343615,0.014667,BEAGLE
img_beagle_10.jpg,0.494222,0.002351,0.441141,0.1,0.335671,0.04205,-0.598587,0.006612,BEAGLE
img_beagle_12.jpg,0.331215,0.000006,0.326745,0.15,0.408092,0.019798,1.555223,0.012856,BEAGLE
img_beagle_13.jpg,0.465458,0.000022,0.522355,0.2,0.369045,0.017641,-0.303355,0.006667,BEAGLE
img_beagle_14.jpg,0.364878,0.001282,0.561403,0.05,0.406019,0.040372,0.669394,0.005801,BEAGLE
img_beagle_15.jpg,0.276636,0.000528,0.327298,0.3,0.482326,0.140373,0.835263,0.007303,BEAGLE
img_german_shorthaired_1.jpg,0.600875,0.000676,0.479715,0.35,0.280061,0.105351,-0.580938,0.00561,GERMAN_SHORTHAIRED
img_german_shorthaired_10.jpg,0.365227,0.026355,0.608735,0.5,0.426373,0.107652,0.681518,0.007427,GERMAN_SHORTHAIRED
img_german_shorthaired_11.jpg,0.421445,0.008503,0.501585,0.65,0.176238,0.097867,0.618,0.005207,GERMAN_SHORTHAIRED
img_german_shorthaired_12.jpg,0.612614,0.000523,0.566642,0.15,0.343465,0.065314,-0.452935,0.005825,GERMAN_SHORTHAIRED
img_german_shorthaired_13.jpg,0.579502,0.00013,0.525309,0.15,0.46174,0.065948,-0.639345,0.006653,GERMAN_SHORTHAIRED
img_german_shorthaired_14.jpg,0.277401,0.000054,0.368611,0.3,0.307231,0.101645,1.745103,0.010519,GERMAN_SHORTHAIRED
img_german_shorthaired_15.jpg,0.492774,0.000112,0.430548,0.15,0.179014,0.050152,-0.776866,0.010237,GERMAN_SHORTHAIRED
````

We now need to remove the string attribute from the dataset. 
This can be done easily from the command-line using WEKA’s RemoveType filter:
````
java -cp class path_to_weka weka.filters.unsupervised.attribute.RemoveType -T string -i dog_features.arff -o dog_features_nostrings.arff
````

##To perform an image classification experiment from the command line
Use the feature-extracted ARFF file to run experiments, e.g.:

````
java -cp classpath_to_weka weka.classifiers.functions.SMO -t dogs_features_nostrings.arff
````

##Sources for the software and data used in this repository

LIRE 0.9.3 https://code.google.com/p/lire/

WEKA 3.7.12 http://www.cs.waikato.ac.nz/ml/weka/

Butterfly & birds images http://www-cvr.ai.uiuc.edu/ponce_grp/data/

Oxford IIIT Pet Dataset http://www.robots.ox.ac.uk/~vgg/data/pets/
