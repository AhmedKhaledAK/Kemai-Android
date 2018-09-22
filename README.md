# Kemai-Android
Read Lists from Firebase in an effective and easy way.

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
    implementation 'com.github.AhmedKhaledAK:Kemai-Android:v1.0-beta'
}
```

### Usage
> Please Note that you will need Gson and Jackson libraries in order to work with this version Kemai: 
```
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.fasterxml.jackson.core:jackson-databind:2.8.5'
implementation 'com.fasterxml.jackson.core:jackson-core:2.8.5'
implementation 'com.fasterxml.jackson.core:jackson-annotations:2.8.5'
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
                    reader.editInMap(map, "fooList1"); // your map that holds the DataSnapshot and the key of the expected list.
                    reader.editInMap(map, "fooList2");
                    // End of Library
                    
                    String jsonResponse = null;
                    try {
                      jsonResponse = new ObjectMapper().writeValueAsString(map);
                    } catch (JsonProcessingException e) {
                      e.printStackTrace();
                    }
                    Gson gson = new Gson();
                    Foo foo = gson.fromJson(jsonResponse, Foo.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
```
> Note that we did not need to set the map again since we did not change its reference.

### Help

Need help? You can ask a question on stackoverflow with the tag "kemai" and I'll reach to you as soon as possible or submit an issue if you encountered one.

### Licence

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
