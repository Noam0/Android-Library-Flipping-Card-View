# FlippingCardView

## 🚀 A Customizable Flipping Card View Library for Android 🚀

`FlippingCardView` enables developers to implement engaging and interactive card flip animations in their Android applications. It is ideal for applications such as games, educational tools, or any scenario where card flipping enhances user interaction.

### Demo

<img src="https://github.com/user-attachments/assets/f48987b5-0330-4e8d-98cb-aea41acabae0" width="230" height="400" alt="finalgif">

## Setup

### Gradle

Add the following to your project's `build.gradle` file to include `FlippingCardView` in your project:

```gradle
repositories {
    google()
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.noam0:flippingcardlibrary:x.y.z'
}


```
## Custom Attributes

`FlippingCardView` offers several custom attributes that allow for extensive customization of the view's appearance and behavior:

### `frontImage`
- **Description**: Specifies the drawable resource used as the front image of the card.
- **Type**: Reference
- **Example**: `app:frontImage="@drawable/front_image"`
- **Usage**: Set this attribute in your XML layout to define what image appears on the front side of the card when it is facing upwards.

### `backImage`
- **Description**: Specifies the drawable resource used as the back image of the card.
- **Type**: Reference
- **Example**: `app:backImage="@drawable/back_image"`
- **Usage**: Use this attribute to set the image displayed on the back of the card. This is the image shown when the card is flipped down.

### `flipStyle`
- **Description**: Determines the style of the flip animation. This attribute can enhance the interactivity of the card by defining how it flips between the front and back images.
- **Type**: String
- **Options**:
  - `"normal"`: A straightforward flip without additional effects.
  - `"withElevation"`: Flips with an elevation effect, creating a shadow that simulates depth.
- **Example**: `app:flipStyle="withElevation"`
- **Usage**: This attribute allows you to choose the visual style of the flipping animation, making the card flip more dynamic and visually appealing.




<h1>How To Use:</h1>
<h3>Integrate FlippingCardView into your layout XML:</h3>

```xml
<com.example.flippingcardlibrary.FlippingCardView
    android:id="@+id/flippingCardView"
    android:layout_width="150dp"
    android:layout_height="200dp"
    app:cardRadius="10dp"
    app:flipStyle="withElevation"
    app:frontImage="@drawable/front_image"
    app:backImage="@drawable/back_image"
    android:layout_margin="8dp" />

```
<h3>Java Setup In Activity</h3>

```java

FlippingCardView flippingCardView = findViewById(R.id.flippingCardView);
flippingCardView.setFlipDuration(300); // Duration in milliseconds
flippingCardView.flipCard(); // Trigger the flip animation
```
