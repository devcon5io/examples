package io.devcon5.examples.mixins;

import static io.devcon5.examples.mixins.Mixin.addMixin;
import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

/**
 * Created by Gerald M�cke on 11.09.2015.
 */

public class MixinTest {

    @Test
    public void testAddJavaMixin() throws Exception {
        //prepare
        String o = "123";

        //act
        MyMixin mx = (MyMixin) addMixin(MyMixin.class).to(o);
        System.out.println(mx);
        System.out.println(mx.hello());

        //assert
        assertEquals("Hello World", mx.hello());

    }

    @Test
    public void testAddJSMixin() throws Exception {
        //prepare
        String o = "world";
        URL script = getClass().getResource("/dynamicMixin.js");

        //act
        ScriptedMixin mx = (ScriptedMixin) addMixin(ScriptedMixin.class).withScript(script).to(o);
        System.out.println(mx);
        System.out.println(mx.helloWorld());
        System.out.println(mx.badHabbitsDieHard());

        //assert
        assertEquals("Hell of a world!", mx.helloWorld());
        assertEquals("I do nasty stuff", mx.badHabbitsDieHard());

    }

    public interface MyMixin {

        default String hello() {

            return "Hello World";
        }
    }

    public interface ScriptedMixin {

        default String helloWorld() {
            return "Hello world";
        }



        default String badHabbitsDieHard(){
            return "I do nasty stuff";
        }

    }




}