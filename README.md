# GifSearcher
Android app to search for the gifs


I encountered a problem while setting up an item for recyclerView.
If I set the height of the ImageViews as wrapContent, then the RecyclerView started lagging (sometimes when I scroll it up, the RecyclerView scrolled itself to the top instantly).
So, I have set a fixed size for the ImageView, but this caused the GIFs to be of an incorrect size. It also resulted in much longer loading times for the GIFs in the ImageView (sometimes more than 2 seconds).


Used resources:

1)https://developer.android.com/topic/libraries/data-binding/expressions#:~:text=Binding%20data,-A%20binding%20class&text=xml%20%2C%20so%20the%20corresponding%20generated,values%20for%20the%20binding%20expressions.
2)https://developer.android.com/reference/android/widget/SearchView.OnQueryTextListener
3)https://copyprogramming.com/howto/delay-call-to-onquerytextchange-in-searchview-onquerytextlistener-with-searchview?utm_content=cmp-true
4)https://github.com/ziginsider/GifSearcher
5)https://developer.android.com/develop/ui/views/layout/recyclerview
6)https://stackoverflow.com/questions/6533942/adding-gif-image-in-an-imageview-in-android
7)https://github.com/bumptech/glide
8)https://github.com/square/retrofit
9)https://youtu.be/S-10lLA0nbk?si=arkJ55YNgtGFYwHj
10)And a lot of others stack overflow posts/Youtube guides! :)
