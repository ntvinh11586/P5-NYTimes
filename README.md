# Project 2 - *NYTimesSearch*

**NYTimesSearch** is an android app that allows a user to search for images on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **X** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can enter a search query that will display a grid of news articles using the thumbnail and headline from the New York Times Search API. 
* [x] Use the ActionBar SearchView or custom layout as the query box instead of an EditText.
* [x] Use the RecyclerView with the StaggeredGridLayoutManager to display improve the grid of image results.
* [x] For different news articles that only have text or have text with thumbnails, use Heterogenous Layouts with RecyclerView.
* [x] User can tap on any article in results to view the contents in an embedded browser.
* [x] User can share a link to their friends or email it to themselves.
* [x] User can click on "settings" which allows selection of advanced search options to filter results.
* [x] Use Parcelable instead of Serializable using the popular Parceler library. 
* [x] User can configure advanced search filters such as: 
  * [x] Begin Date (using a date picker)
  * [x] News desk values (Arts, Fashion & Style, Sports)
  * [x] Sort order (oldest or newest)
* [x] Subsequent searches will have any filters applied to the search results.	

The following **optional** functionality is completed:
* [x] User	can	tap	on	any	article	in	results	to	view	the	contents	in	an	embedded	browser.
* [x] User can scroll down "infinitely" to continue loading more news articles. (Note: the maximum number of articles is limited by the API search.)
* [x] Apply the ButterKnife library to reduce view boilerplate.
* [x] Replace Picasso with Glide for more efficient image rendering.
* [ ] Leverage the popular GSON library to streamline the parsing of JSON data.

The following **additional** features are implemented:

* [x] Improve the user interface and experiment with image assets and/or styling and coloring
* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![Video Walkthrough](http://i.imgur.com/S1TUMLP.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright 20016 The-Vinh Nguyen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
