/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodimdrawingapp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
    public record DrawCall(int x1,int y1,int x2,int y2,int shape,Color color1,Color color2,boolean filled,boolean useGradient,boolean dashed,int lineWidth,int dashLength)
        {

        public DrawCall
        {
            if(useGradient&&(color2==null||color1==null))
                throw new DrawCallException(
                    "Cannot create a gradient with only one color!");
            assert shape>=0;
            assert lineWidth>=0;
            assert x1>=0;
            assert x2>=0;
            assert y1>=0;
            assert y2>=0;
        }

        static class DrawCallException extends RuntimeException
        {
            public DrawCallException(String message)
            {
                super(message);
            }
        }

        public MyShapes generateShape()
        {
            Point a=new Point(x1,y1);
            Point b=new Point(x2,y2);
            Paint paint=useGradient?new GradientPaint(
                0f,0f,color1,
                50f,
                50f,
                color2,true):color1;
            BasicStroke stroke=dashed?new BasicStroke(lineWidth,
                                                      BasicStroke.CAP_ROUND,
                                                      BasicStroke.JOIN_ROUND,10,
                                                      new float[]
                                                      {
                                                          dashLength
                                                      },0)
                :new BasicStroke(
                    lineWidth,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND);
            return shape==SHAPE_LINE?new MyLine(a,b,
                                                paint,
                                                stroke):shape==SHAPE_OVAL?new MyOval(
                a,b,paint,stroke,filled):new MyRectangle(a,b,
                                                         paint,
                                                         stroke,filled); // there is no error case
        }

        @Override
        public String toString()
        {
            return "%s: <%d, %d> & <%d, %d> {%s, %s, %d}".formatted(
                switch(shape)
            {
                case SHAPE_LINE ->
                    "LINE";
                case SHAPE_OVAL ->
                    "OVAL";
                case SHAPE_RECTANGLE ->
                    "RECT";
                default ->
                    "???"; // prob wont happen
            },
                x1,y1,x2,y2,
                color1.
                    toString(),
                color2.
                    toString(),dashLength
            );
        }
    }

    static JButton createButton(String label,Consumer<ActionEvent> listener)
    {
        JButton b=new JButton(label);
        b.addActionListener(listener::accept);
        return b;
    }

    static JCheckBox createCheckbox(String label,boolean initialValue,Consumer<Boolean> listener)
    {
        JCheckBox c=new JCheckBox(label,initialValue);
        c.addItemListener((ItemEvent event)->listener.accept(event.
            getStateChange()==1));
        return c;
    }

    static JSpinner createSpinner(int curr,int min,int max,int step,Consumer<Integer> listener)
    {
        JSpinner s=new JSpinner(new SpinnerNumberModel(curr,min,max,step));
        s.addChangeListener((ChangeEvent event)->listener.accept((Integer)s.
            getValue()));
        return s;
    }

    static final class ChangeListener<T>
    {
        public T value;
        private Runnable listener;

        public ChangeListener(T initial)
        {
            this.value=initial;
        }

        public void setListener(Runnable listener)
        {
            this.listener=listener;
        }

        public void notifyListener()
        {
            if(listener!=null)
                listener.run();
        }
    }

    static final int SHAPE_RECTANGLE=0;
    static final int SHAPE_OVAL=1;
    static final int SHAPE_LINE=2;

    static final int DASH_LENGTH_SPINNER_MAX=100;
    static final int LINE_WIDTH_SPINNER_MAX=100;

    private final JPanel controlsPanel;
    private final JPanel controlsRow1;
    private final JPanel controlsRow2;
    private final DrawPanel drawPanel;

    private Color kFirstColor=Color.BLACK;
    private Color kSecondColor=Color.WHITE;
    private int kShape=SHAPE_RECTANGLE;
    private boolean kFilled=false;
    private boolean kGradient=false;
    private boolean kDashed=false;
    private int kLineWidth=4;
    private int kDashLength=8;
    private int kX1;
    private int kY1;
    private int kX2;
    private int kY2;
    private final ChangeListener<Point2D> kMouseLocation;

    DrawCall compile()
    {
        return new DrawCall(kX1,kY1,kX2,kY2,kShape,kFirstColor,
                            kSecondColor,kFilled,kGradient,
                            kDashed,kLineWidth,kDashLength);
    }

    void postDrawCall()
    {
        drawPanel.pushDrawCall(compile());
        drawPanel.repaint(75L);
    }
    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.

    // create the widgets for the firstLine Panel.
    //create the widgets for the secondLine Panel.
    // Variables for drawPanel.
    // add status label
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
        setTitle("Java 2D Drawings");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        kMouseLocation=new ChangeListener<>(new Point2D.Float(0F,0F));
        drawPanel=new DrawPanel();
        controlsPanel=new JPanel();
        controlsPanel.setOpaque(true);
        controlsPanel.setBackground(Color.CYAN);
        controlsPanel.setPreferredSize(new Dimension(getPreferredSize().width,
                                                     100));
        controlsPanel.setLayout(new BoxLayout(controlsPanel,BoxLayout.Y_AXIS));
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(6,0,
                                                                6,0));
        controlsRow1=new JPanel();
        controlsRow1.setOpaque(false);
        controlsRow1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JComboBox<String> shapesSelector=new JComboBox<>(new String[]
        {
            "Rectangle","Oval","Line"
        });
        shapesSelector.addItemListener((ItemEvent event)->kShape=shapesSelector.
            getSelectedIndex());
        controlsRow1.add(new JLabel("Shape:"));
        controlsRow1.add(shapesSelector);
        controlsRow1.add(createButton("1st Color...",(ActionEvent ignored)->
                                  {
                                      kFirstColor=JColorChooser.showDialog(this,
                                                                           "Choose 1st color",
                                                                           kFirstColor);
                                  }));
        controlsRow1.add(createButton("2nd Color...",(ActionEvent ignored)->
                                  {
                                      kSecondColor=JColorChooser.
                                          showDialog(this,
                                                     "Choose 2nd color",
                                                     kSecondColor);
                                  }));
        controlsRow1.add(createButton("Undo",(ActionEvent ignored)->
                                  {
                                      drawPanel.
                                          popDrawCall();
                                      drawPanel.repaint();
                                  }));
        controlsRow1.add(createButton("Clear",(ActionEvent ignored)->
                                  {
                                      drawPanel.
                                          clearDrawCalls();
                                      drawPanel.repaint();
                                  }));
        controlsPanel.add(controlsRow1);
        controlsRow2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlsRow2.setOpaque(false);
        controlsRow2.add(new JLabel("Options:"));
        controlsRow2.
            add(createCheckbox("Filled",false,(Boolean value)->kFilled=value));
        controlsRow2.add(createCheckbox("Use Gradient",false,
                                        (Boolean value)->kGradient=value));
        controlsRow2.
            add(createCheckbox("Dashed",false,(Boolean value)->kDashed=value));
        controlsRow2.add(new JLabel("Line Width:"));
        controlsRow2.add(createSpinner(4,1,DASH_LENGTH_SPINNER_MAX,1,
                                       (Integer i)->kLineWidth=i));
        controlsRow2.add(new JLabel("Dash Width:"));
        controlsRow2.add(createSpinner(8,1,LINE_WIDTH_SPINNER_MAX,1,
                                       (Integer i)->kDashLength=i));
        controlsPanel.add(controlsRow2);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlsPanel,BorderLayout.NORTH);
        getContentPane().add(drawPanel,BorderLayout.CENTER);
        JLabel mouseLocationLabel=new JLabel();
        kMouseLocation.setListener(()->mouseLocationLabel.setText("(%d, %d)".
            formatted(Math.round(kMouseLocation.value.getX()),
                      Math.round(kMouseLocation.value.getY()))));
        kMouseLocation.notifyListener();
        getContentPane().add(mouseLocationLabel,BorderLayout.SOUTH);
        // add widgets to panels
        // firstLine widgets
        // secondLine widgets
        // add top panel of two panels
        // add topPanel to North, drawPanel to Center, and statusLabel to South
        //add listeners and event handlers
    }

    // Create event handlers, if needed
    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {
        private final ArrayList<DrawCall> drawCalls;
        private boolean drawingGuide=false;

        public DrawPanel()
        {
            drawCalls=new ArrayList<>();
            MouseHandler mouseHandler=new MouseHandler();
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        public void pushDrawCall(DrawCall drawCall)
        {
            this.drawCalls.addLast(drawCall);
        }

        public DrawCall popDrawCall()
        {
            return this.drawCalls.removeLast();
        }

        public void clearDrawCalls()
        {
            this.drawCalls.clear();
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d=(Graphics2D)g;
            //loop through and draw each shape in the shapes arraylist
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                                 RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                                 RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                                 RenderingHints.VALUE_STROKE_NORMALIZE);
            for(DrawCall drawCall:drawCalls)
                drawCall.generateShape().draw(g2d);
            if(drawingGuide)
            {
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                                     RenderingHints.VALUE_RENDER_SPEED);
                g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                                     RenderingHints.VALUE_COLOR_RENDER_SPEED);
                g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                                     RenderingHints.VALUE_STROKE_PURE);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_OFF);
                compile().generateShape().draw(g2d);
//                g2d.setStroke(new BasicStroke(1f,BasicStroke.CAP_ROUND,
//                                              BasicStroke.JOIN_ROUND));
//                g2d.setColor(Color.LIGHT_GRAY);
//                switch(kShape)
//                {
//                    case SHAPE_OVAL ->
//                        g2d.drawOval(Math.min(kX1,kX2),Math.min(kY1,kY2),
//                                     Math.abs(kX2-kX1),Math.abs(kY2-kY1));
//                    case SHAPE_RECTANGLE ->
//                        g2d.drawRect(Math.min(kX1,kX2),Math.min(kY1,kY2),
//                                     Math.abs(kX2-kX1),Math.abs(kY2-kY1));
//                    case SHAPE_LINE ->
//                        g2d.drawLine(kX1,kY1,kX2,kY2);
//                }
            }
            g2d.dispose();
        }

        private class MouseHandler extends MouseAdapter implements
            MouseMotionListener
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                drawingGuide=true;
                kX1=event.getX();
                kY1=event.getY();
                kX2=kX1; // prevents passthrough
                kY2=kY1; // prevents passthrough
                repaint(75L);
            }

            @Override
            public void mouseReleased(MouseEvent event)
            {
                kX2=event.getX();
                kY2=event.getY();
                drawingGuide=false;
                postDrawCall();
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
                kX2=event.getX();
                kY2=event.getY();
                kMouseLocation.value.setLocation(event.getX(),event.getY());
                kMouseLocation.notifyListener();
                repaint(75L);
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {
                kMouseLocation.value.setLocation(event.getX(),event.getY());
                kMouseLocation.notifyListener();
            }
        }

    }
}
