## Contributions 

Noah Parkes 
1. gaussian blur 
2. image flip

Maiek Anantawat 

1. colour channel cycle 
2. error avoidence/prevention 
3. read me file

Damian Fraser 

1. alter actions drop down menu 
2. image colour inversion 
3. image rotate

Jan Faller 

1. median filter 
2. export function

Parsa Orodes 

1. mutilingual support 
2. sharpen filter 
3. resize 
4. soft blur 


## How we tested the code 
1. checked that filters work as intended on the images. Also JUnit tested the Gaussian and Median filters. 
2. checked that file actions worked as intended and had the right file format - Exception handled wrong/missing file formats 
3. we also had people that are not taking this paper to have a go at using the program we have created - user tested 

## Known issues/bugs 
1. the median filter has a slow runtime 

## User guide 
1. You can find the option to open, save, save as and export an image under the file option in the task bar
2. the undo and redo operations are listed under edit in the task bar 
3. the options to rotate the image 90 degrees to the right, 90 degrees to the left and 180 degrees are listed under alter in the task bar 

    the options to filp the image vertically and horizontally are also listed under alter 

    lastly the resize operation which asks the user for the width and height which they want to change the image to, then applies it. This is also listed under alter. The maximum dimension the image is allowed to get to is 1500 by 1500. 

4. you can find the zoom in, zoom out and zoom full options listed under view in the task bar 
5. You can find options to change languages between English and Spanish listed under language 
6. you can find the options add filters to your image listed under filter in the task bar these include 

    a. the mean filter  

    b. soft blur filter 

    c. sharpen filter 

    d. median filter

    e. gaussian blur filter 


7. you can also find the options to alter the colours of your image listed under colour in the task bar these include 

    a. grayscale, which changes the image colour to grey 

    b. colour channel cycle, which rotates the red, blue and green colours of the image around based on the option selected by the user 

    c. Invert colours, which changes the red, blue and green values to their negative values 

## Code refrectoring/additional build instructions  
1. we added a protected method within the andie file called restartAndShowGUI which is similar to createAndShowGUI. However, instead of having a new image panel set up it uses the existing image panel with the its already editable image. This method's aim is just to change jpanel text. This was done to switch the jpanel texts between different languages whilst preserving the editing done on the images.  