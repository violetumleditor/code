package com.horstmann.violet.framework.util.string.decorator;

import com.horstmann.violet.framework.util.string.OneLineString;

/**
 * Created by Adrian Bobrowski on 12.12.2015.
 */
public class ItalicsOneLineStringDecorator  extends OneLineStringDecorator {

    public ItalicsOneLineStringDecorator(OneLineString decoratedOneLineString)
    {
        super(decoratedOneLineString);
    }

    public ItalicsOneLineStringDecorator(OneLineString decoratedOneLineString, String labelPrefix)
    {
        super(decoratedOneLineString);
        this.labelPrefix = labelPrefix;
    }

    @Override
    public String toHTML()
    {
        return "<b>" + decoratedOneLineString.toHTML() + "</b>";
    }
}