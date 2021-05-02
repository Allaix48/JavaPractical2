package Tree.BTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {
    BTree<Integer> tree;
    @BeforeEach
    public  void init()
    {
        tree = new BTree<Integer>(3);


    }
    @Nested
    class ConstructTests{
        @TempDir
        File anotherTempDir;

        @Test
        void givenFieldWithTempDirectoryFile_whenWriteToFile_thenContentIsCorrect() throws IOException {
            assertTrue(this.anotherTempDir.isDirectory(), "Should be a directory ");

            File letters = new File(anotherTempDir, "letters.txt");
            List<String> lines = Arrays.asList("x", "y", "z");

            Files.write(letters.toPath(), lines);

            assertAll(
                    () -> assertTrue(Files.exists(letters.toPath()), "File should exist"),
                    () -> assertLinesMatch(lines, Files.readAllLines(letters.toPath())));
        }

    }

    @Nested
    class DeleteTests{

    }
    @Nested
    class FindTests{

    }

    @Nested
    class InsertTests{
        @ParameterizedTest
        @ValueSource(ints = {2, 0, -2, Integer.MAX_VALUE, Integer.MIN_VALUE})
        void testPutVal_WhenAndPut1Val_ThenShouldBeOk(Integer val) {

            tree.insert(val);
            Node<Integer> newNode =   tree.Root;
            assertEquals(val, newNode.Keys.get(0));

        }

        @Test
        void testPutVal_WhenPutManyValLessThenRank_ThenShouldBeOk() {

            tree.insert(1);
            tree.insert(2);
            Node<Integer> newNode =   tree.Root;
            assertAll(
                    () -> assertEquals((Integer) 1, newNode.Keys.get(0)),
                    () -> assertEquals((Integer) 2, newNode.Keys.get(1))
            );
        }

        @Test
        void testSplit_123() {

             tree.insert(1);
             tree.insert(2);
             tree.insert(3);
            Node<Integer> newNode =   tree.Root;
            assertAll(

                    () -> assertEquals((Integer) 1, newNode.Childes.get(0).Keys.get(0)),
                    () -> assertEquals((Integer) 2, newNode.Keys.get(0)),
                    () -> assertEquals((Integer) 3, newNode.Childes.get(1).Keys.get(0))
            );
        }
        @Test
        void testSplit_1234() {
             tree.insert(1);
             tree.insert(2);
             tree.insert(3);
             tree.insert(4);
            Node<Integer> newNode =   tree.Root;
            assertAll(


                    () -> assertEquals((Integer) 1, newNode.Childes.get(0).Keys.get(0)),
                    () -> assertEquals((Integer) 2, newNode.Keys.get(0)),
                    () -> assertEquals((Integer) 3, newNode.Childes.get(1).Keys.get(0)),
                    () -> assertEquals((Integer) 4, newNode.Childes.get(1).Keys.get(1))
            );



        }
        @Test
        void testSplit_12345() {
             tree.insert(1);
             tree.insert(2);
             tree.insert(3);
             tree.insert(4);
             tree.insert(5);
            Node<Integer> newNode =   tree.Root;
            assertAll(
                    () -> assertEquals((Integer) 1, newNode.Childes.get(0).Keys.get(0)),
                    () -> assertEquals((Integer) 2, newNode.Keys.get(0)),
                    () -> assertEquals((Integer) 4, newNode.Keys.get(1)),
                    () -> assertEquals((Integer) 3, newNode.Childes.get(1).Keys.get(0)),
                    () -> assertEquals((Integer) 5, newNode.Childes.get(2).Keys.get(0)),
                    () -> assertEquals( 3, newNode.Childes.size())
            );
        }
        @Test
        void testSplit_123456() {
             tree.insert(1);
             tree.insert(2);
             tree.insert(3);
             tree.insert(4);
             tree.insert(5);
             tree.insert(6);
            Node<Integer> newNode =   tree.Root;
            assertAll(
                    () -> assertEquals((Integer) 1, newNode.Childes.get(0).Keys.get(0)),
                    () -> assertEquals((Integer) 3, newNode.Childes.get(1).Keys.get(0)),
                    () -> assertEquals((Integer) 2, newNode.Keys.get(0)),
                    () -> assertEquals((Integer) 4, newNode.Keys.get(1)),
                    () -> assertEquals((Integer) 5, newNode.Childes.get(2).Keys.get(0)),
                    () -> assertEquals((Integer) 6, newNode.Childes.get(2).Keys.get(1)),
                    () -> assertEquals( 3, newNode.Childes.size())
            );
        }
        @Test
        void testSplit_1234567() {
             tree.insert(1);
             tree.insert(2);
             tree.insert(3);
             tree.insert(4);
             tree.insert(5);
             tree.insert(6);
             tree.insert(7);
            Node<Integer> newNode =   tree.Root;
            Node<Integer> Child2 = newNode.Childes.get(0);
            Node<Integer> Child6 = newNode.Childes.get(1);


            assertAll(
                    () -> assertEquals((Integer) 4, newNode.Keys.get(0)),
                    () -> assertEquals((Integer) 2, Child2.Keys.get(0)),
                    () -> assertEquals((Integer) 6, Child6.Keys.get(0)),
                    () -> assertEquals((Integer) 1, Child2.Childes.get(0).Keys.get(0)),
                    () -> assertEquals((Integer) 3, Child2.Childes.get(1).Keys.get(0)),
                    () -> assertEquals((Integer) 5, Child6.Childes.get(0).Keys.get(0)),
                    () -> assertEquals((Integer) 7, Child6.Childes.get(1).Keys.get(0))

            );
        }

    }

}