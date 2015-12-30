package com.horstmann.violet.framework.graphics.content;

import com.horstmann.violet.framework.graphics.Separator;
import com.horstmann.violet.framework.graphics.content.Content;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Adrian Bobrowski on 21.12.2015.
 */
public abstract class GroupContent extends Content
{
    protected abstract Point2D getNextOffset(Point2D beforeOffset, Content content);
    protected abstract Point2D getStartPointSeparator(Point2D offset);
    protected abstract Point2D getEndPointSeparator(Point2D offset);

    public final void add(Content content)
    {
        if(null == content)
        {
            throw new NullPointerException("Content can't be null");
        }
        content.addParent(this);
        contents.add(content);
        refresh();
    }

    public final void remove(Content content)
    {
        if(null == content)
        {
            throw new NullPointerException("Content can't be null");
        }
        content.removeParent(this);
        contents.remove(content);
        refresh();
    }

    public final Separator getSeparator()
    {
        return separator;
    }
    public final void setSeparator(Separator separator) {
        if(null==separator)
        {
            separator = Separator.EMPTY;
        }
        this.separator = separator;
    }

    protected final List<Content> getContents()
    {
        return contents;
    }

    protected final void setContentsWidth(int width)
    {
        for (Content content: contents) {
            content.setWidth(width);
        }
    }
    protected final void setContentsHeight(int height)
    {
        for (Content content: contents) {
            content.setHeight(height);
        }
    }

    @Override
    public final void draw(Graphics2D g2)
    {
        Content content = null;
        Point2D offset = new Point2D.Double(0,0);
        Iterator<Content> iterator = contents.iterator();

        if(iterator.hasNext())
        {
            content = iterator.next();
            content.draw(g2, offset);
            offset = getNextOffset(offset, content);
        }
        while(iterator.hasNext())
        {
            content = iterator.next();
            content.draw(g2, offset);
            separator.draw(g2, getStartPointSeparator(offset), getEndPointSeparator(offset));
            offset = getNextOffset(offset, content);
        }
    }

    private Separator separator = Separator.EMPTY;
    private List<Content> contents = new ArrayList<Content>();
}