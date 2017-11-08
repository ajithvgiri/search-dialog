[![](https://jitpack.io/v/ajithvgiri/SearchDialog.svg)](https://jitpack.io/#ajithvgiri/SearchDialog)

# SearchDialog
Android Search Dialog Library

# Setup
## 1. Provide the gradle dependency

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the gradle dependency to your `app` module `build.gradle` file:

```
	dependencies {
	         compile 'com.github.ajithvgiri:SearchDialog:v1.0'
	}

```

## 2. Initialization of the SearchDialog
<SearchListItem> model contains id and title
``` java
      List<SearchListItem> searchListItems = new ArrayList<>();
      SearchableDialog  searchableDialog = new SearchableDialog(this, searchListItems, "Title");
```

## 3. Show the SearchDialog

``` java
        searchableDialog.show();
```

## 4. Get Selected Item from the SearchDialog

``` java
        searchableDialog.setOnItemSelected(new OnSearchItemSelected() {
           @Override
           public void onClick(int position, SearchListItem searchListItem) {
               // searchListItem.getId(); returns id
               // searchListItem.getTitle(); returns title
           }
        });
```
## 5. Screen Shots

![Search Dialog](https://i.imgur.com/47IHtQH.png)
