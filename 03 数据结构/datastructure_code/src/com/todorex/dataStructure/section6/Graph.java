package com.todorex.dataStructure.section6;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by zejian on 2018/1/20.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 图的基类
 */
public abstract class Graph {

    private Scanner scanner;
    /**
     * 顶点的个数
     *
     * @return
     */
    public abstract int V();

    /**
     * 边的个数
     * @return
     */
    public abstract int E();

    /**
     * 添加一条边
     * @param v
     * @param w
     */
    public abstract void addEdge(int v, int w);

    /**
     *  判断图中是否有从v到w的边
     * @param v
     * @param w
     * @return
     */
    public abstract boolean hasEdge(int v , int w);

    /**
     * 获取v顶点的所有邻边
     *
     * @param v
     * @return
     */
    public abstract Iterable<Integer> adj(int v);


    /**
     * 从文件读取一张图
     * @param filename
     */
    public void readGraph(String filename){

        readFile(filename);

        try {
            int V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            assert V == this.V();

            int E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                this.addEdge(v, w);
            }
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
    }

    private void readFile(String filename){
        assert filename != null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }
            else
                throw new IllegalArgumentException(filename + "doesn't exist.");
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + filename, ioe);
        }
    }

}
