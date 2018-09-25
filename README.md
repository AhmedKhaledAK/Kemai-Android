# Kemai-Android
Read Lists from Firebase in an easy way.

Firebase doesn't deal with lists / arrays, but sometimes we need to read simple lists to use it in our app. This Library enables you to read Lists from any JSON generators that do not support lists / arrays.

Kemai deals with these errors: 
* Expected BEGIN_ARRAY but found BEGIN_OBJECT. This error can be found when using JSON libraries such as GSON when dealing with JSON generators that do not support lists / arrays.
* Firebase deserialization.

### Download
* In your top-level build.gradle file, add `maven { url 'https://jitpack.io' }` at the end of `repositories`:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
* Add this to your dependencies:
```
dependencies {
    ...
    implementation 'com.github.AhmedKhaledAK:Kemai-Android:v1.0-beta.1'
}
```

### Usage
> Please Note that you will need Gson library in order to work with this version of Kemai: 
```
implementation 'com.google.code.gson:gson:2.8.5'
```
* Read data from a node in Firebase:
```java
firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Foo foo = ds.getValue(Foo.class); // Delete this line
                    // Use this:
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                    //Kemai Library:
                    KListReader reader = new KListReader();
                  
                    String jsonResponse = null;
                    try {
                      jsonResponse = reader.convertMapToJSONString(map, "fooList1", "fooList2");
                    } catch (JsonProcessingException e) {
                      e.printStackTrace();
                    }
                    //End of Library
                    Gson gson = new Gson();
                    Foo foo = gson.fromJson(jsonResponse, Foo.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
```
> Note that we did not need to set the map again since we did not change its reference. Also you can pass as many lists as you like as parameters to convertMapToJSONString() method.

### License

```
MIT License

Copyright (c) 2018 Ahmed Khaled

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
