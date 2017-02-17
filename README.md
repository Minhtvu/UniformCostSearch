# UniformCostSearch
Implement the uniform cost search algorithm that can find a route between any two cities, and this program will take exactly three command line arguments, as follows:

UniformCostSearch input_filename origin_city destination_city

An example command line is:
UniformCostSearch input1.txt Munich Berlin

Argument input_filename is the name of a text file such as input1.txt, that describes road connections between cities in some part of the world. For example, the road system described by file input1.txt can be visualized in Figure 1 shown above. You can assume that the input file is formatted in the same way as input1.txt: each line contains three items. The last line contains the items "END OF INPUT", and that is how the program can detect that it has reached the end of the file. The other lines of the file contain, in this order, a source city, a destination city, and the length in kilometers of the road connecting directly those two cities. Each city name will be a single word (for example, we will use New_York instead of New York), consisting of upper and lowercase letters and possibly underscores.

The program will compute a route between the origin city and the destination city, and will print out both the length of the route and the list of all cities that lie on that route. For example,

UniformCostSearch input1.txt Bremen Frankfurt

should have the following output:

distance: 455.0 km
route: 
Bremen to Dortmund, 234.0 km 
Dortmund to Frankfurt, 221.0 km 


AND

UniformCostSearch input1.txt London Frankfurt

should have the following output:

distance: infinity
route: 
none
