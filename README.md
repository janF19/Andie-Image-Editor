# ANDIE Part 1

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
5. JUnit Testing 


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

---

# ANDIE Part 2

## Contributions 

Noah Parkes

1. Extended filters 
2. Brightness adjustment 
3. Contrast adjustment 

Maiek Anantawat

1. Keyboard shortcuts 
2. Mouse selection of rectangular regions 
3. Show us something 
4. read me file

Damian Fraser

1. Random scattering 
2. Toolbar for common operations 
3. Crop to selection

Jan Faller

1. Filters with negative results 
2. Drawing functions 
3. Macros for record and replay of operations 

Parsa Orodes

1. Emboss and edge detection 
2. Block averaging 
3. Mouse selection of rectangular regions 

## How we tested the code 

-  For each filter/image operation it was tested with each other operation to check their integrity and consistency 
-  When seeing an image operation does as its expected it was often compared with the examples shown on the COSC202 lab book 
-  Mouse base region selection — > pixel value were printed manually as output to check the right ones were selection 
-  I used a default excel template when writing the test cases 
-  For embos and other filters with negative results we tested to see if were like the expected image example on the lab book as unit testing them would have taken too long because …. 
- For a list of test cases please refer to [Document](Test_cases.xlsx)

## Known issues/bugs 

1. The keyboard shortcut for exporting images (Alt-E) needs to be pressed twice on MacOS based devices but works as expected on Windows based devices. We are aware of this issue but unfortunately havent been able to resolve this issue as of yet. 
2. Features that implement mouse region selection like croping and drawing are unable to be used with macros, if the selected region is out of bounds of the current images size. We are aware of this issue but are unable to resolve this issue at this time. However, we have provided a warning pop-up message to the user that prevents the macros to be loaded and lets the user know why. 

## User guide 

For the second part of our ANDIE project we have added multiple new feature to ANDIE, while also improving upon some of the existing features within ANDIE. 

1. We have extended some of the pre existing image filters within andie to better account for the edges of images. These includes the mean, sharpen, gaussian and median filters. 
2. We have also updated the filter implementation to allow for both negative and positive values. 
3. Under the filters menu in the task bar we have added multiple new filters for the user to chose from these include

    - the embos filter. I thought of using the picture that the user is currently editing for the emboss filter options and show casing what each of the embos filter would do on it. However I thought what if the user wants to look at what the embos filters do but they haven’t uploaded a picture yet. This way a default picture is better that show cases what the filters do to an original photo as a “before and after”. 
    - the sobel filter 
    - the block average filter. The maximum block height/width is set to the 1/3 of the height and width of the image (which is still quite a large input value). This is because inputs higher than this would simply pixelate (Average) the whole picture into one mono pixel and it doesn’t make sense to let the block averaging to this. Setting it at 1/3 still lets allows for a few pixels to be displayed.

    - the random scatter filter 
4. We have also added some new actions to alter the colours of an image, listed under colour in the task bar these include 

    - a brighness/contrast action 
    - a warmth action (show us something)
    - a vibrance action (show us something)
5. We have added a tool bar to andie, which can be found under the menu/taskbar. This toolbar provides an easy and quick way for the user to access and use common operations in andie like undo/redo. 
6. Keyboard shortcuts have been added for all operations in the menu/taskbar and a shortcut for a given action can be found by hovering over the action in the menu/taskbar listed next to the operations name
7. Mouse-based region selection has been implmented in the image panel class and highlights the selected region with a white semi-transparent box for the user to see
8. In the toolbar under the menu/taskbar in addition to the common operations we have also added 

    - the option to crop the image 
    - the option to draw on the image 

9. Macros for record and replay have been implemented and listed under macro in the menu/taskbar with options to 

    - record
    - stop 
    - reload 

10. For our extension to andie we have added two new colour operations both listed under colour in the menu/taskbar 

    - Vibrance, which adjusts how vibrant the colour of the image are 
    - Warmth, which adjusts the images colours based on temperature. Orange for a warmer look and blue for a colder look. 
