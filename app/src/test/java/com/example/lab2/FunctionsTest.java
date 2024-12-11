package com.example.lab2;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class FunctionsTest {

    // Unit tests for Functions class
    @Test
    public void testCountWords() {
        String inputText = "This is a test";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Words", inputText);
        assertEquals(4, result);
    }

    @Test
    public void testCountCharacters() {
        String inputText = "Hello";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Symbols", inputText);
        assertEquals(5, result);
    }

    @Test
    public void testCountEmptyText() {
        String inputText = "  ";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Words", inputText);
        assertEquals(-1, result); // Verify that empty input returns -1
    }

    @Test
    public void testInvalidOption() {
        String inputText = "Hello world";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Invalid", inputText);
        assertEquals(-2, result); // Check that an invalid option returns -2
    }

    @Test
    public void testCountWordsWithPunctuation() {
        String inputText = "Hello, world! How's it going?";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Words", inputText);
        assertEquals(5, result);
    }

    @Test
    public void testCountCharactersWithSpaces() {
        String inputText = "Hello world";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Symbols", inputText);
        assertEquals(11, result);
    }

    @Test
    public void testCountCharactersWithSpecialChars() {
        String inputText = "@Hello#World$";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Symbols", inputText);
        assertEquals(13, result);
    }

    @Test
    public void testCountWordsWithNewLines() {
        String inputText = "Hello\nWorld\nThis is a test";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Words", inputText);
        assertEquals(5, result);
    }

    @Test
    public void testCountWordsWithTabs() {
        String inputText = "Hello\tWorld\tThis is a test";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Words", inputText);
        assertEquals(5, result);
    }

    @Test
    public void testCountWordsWithMixedWhitespace() {
        String inputText = "Hello \t World \n This   is a    test";
        int result = Functions.countText(ApplicationProvider.getApplicationContext(), "Words", inputText);
        assertEquals(6, result);
    }

    // UI/Integration tests for MainActivity
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testWordCountUI() {
        Espresso.onView(ViewMatchers.withId(R.id.edUserInputText))
                .perform(ViewActions.typeText("This is a UI test"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.spSelector))
                .perform(ViewActions.click());

        Espresso.onData(org.hamcrest.Matchers.allOf(org.hamcrest.Matchers.is("Words")))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnCount))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.countView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Count: 5")));
    }

    @Test
    public void testSymbolCountUI() {
        Espresso.onView(ViewMatchers.withId(R.id.edUserInputText))
                .perform(ViewActions.typeText("Hello UI"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.spSelector))
                .perform(ViewActions.click());

        Espresso.onData(org.hamcrest.Matchers.allOf(org.hamcrest.Matchers.is("Symbols")))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnCount))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.countView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Count: 8")));
    }

    @Test
    public void testEmptyInputUI() {
        Espresso.onView(ViewMatchers.withId(R.id.edUserInputText))
                .perform(ViewActions.clearText());

        Espresso.onView(ViewMatchers.withId(R.id.spSelector))
                .perform(ViewActions.click());

        Espresso.onData(org.hamcrest.Matchers.allOf(org.hamcrest.Matchers.is("Words")))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnCount))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.countView))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));
    }
}