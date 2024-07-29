# Universities_Information
This application is developed using Java and Android Studio. It displays basic information about a university selected from a list. The application has **three** activities: <br> 
• **Activity 1**: Displays a list of 8 countries using a **ListView**. The country list is stored as a string resource. The country flag are displayed next to every country name. Tapping on a country name brings Activity 2. <br>
• **Activity 2**: Displays a list of universities in the selected country (from Activity 1). The list of universities for a given country are obtained using a free web API (check entry 14 @ https://apipheny.io/free-api/) <br>
• **Activity 3**: Displays the details of the selected university (from Activity 2). The displayed information includes: The name and the domain. This activity has two buttons: <br>
o **OK**: to close the activity.<br>
o **Visit**: to open the university webpage using the URL returned from calling the API.
