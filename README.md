# product_shower

Appliaction Recuarments


Part 1. Create screen design (see attached "screen.jpg").

The design of the screen must be created with XML using constraint layout.

Part 2. Create an app that will show a main categories' list. When each item(category) is clicked a new page should open with a new list where the subcategories of that main category will appear. After the subcategory list the products of the main category are shown.

-The properties of categories and products are not essential

-Those main categories and subcategories which don't contain any product or subcategory should not appear in the list

-The subcategories may have sub-subcategories

-Provide test data to the app to demonstrate the performance


Application structure details:


· For Application Architecture used MVVM

· For observing data used RxJava and Gson for mapping json to data

· For image(from URL) showing used Glide

· For UI used Material Design, Material components and ConstraintLayout

· For listing items used RecyclerView with corresponding layout manager(LinearLayout)

·  For separating subcategories and products used TabLayout with Tab items 

Status of the work:
·  Part 1 - Done

·  Part 2 - Done(as I understood the test requirements) 
 
The app data represented as json and located in app assets folder with name 'content.json'. 
