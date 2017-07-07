# import the necessary packages
from collections import deque
import numpy as np
import argparse
import imutils
import cv2

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-v", "--video",
	help="path to the (optional) video file")
ap.add_argument("-b", "--buffer", type=int, default=32,
	help="max buffer size")
args = vars(ap.parse_args())

# declare a video file to write the processed frames to
width = 640
height = 480
fourcc = cv2.VideoWriter_fourcc('m','p','4','v')
processedVid = cv2.VideoWriter('processed.m4v', fourcc, 30.0, (width, height))

# if a video path was not supplied, grab the reference
# to the webcam
if not args.get("video", False):
	camera = cv2.VideoCapture(0)

# otherwise, grab a reference to the video file
else:
	camera = cv2.VideoCapture(args["video"])

while True:
    (grabbed, frame) = camera.read()

    if args.get("video") and not grabbed:
	    break
    
    frame = cv2.resize(frame, (width, height))
    # show the frame to our screen and increment the frame counter
    cv2.imshow("Frame", frame)
    key = cv2.waitKey(1) & 0xFF

	# save to the processed file
    processedVid.write(frame)

	# if the 'q' key is pressed, stop the loop
    if key == ord("q"):
	    break

# cleanup the camera and close any open windows
camera.release()
processedVid.release()
cv2.destroyAllWindows()
