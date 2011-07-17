package com.tda.presentation.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Collection;

import com.tda.model.dentist.Tooth;

public class ToothEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        ArrayList<Tooth> ret = new ArrayList<Tooth>();
        String[] tooths = text.split(";");

        for ( int i=0 ; i < tooths.length ; i++ ) {
            String [] parts = tooths[i].split(":");
            Tooth tooth = new Tooth();
            tooth.setNumber(i);
            tooth.setNorth(parts[0]);
            tooth.setEast(parts[1]);
            tooth.setSouth(parts[2]);
            tooth.setWest(parts[3]);
            tooth.setCenter(parts[4]);
            ret.add(tooth);
        }

        setValue(ret);
    }

    @Override
    public String getAsText() {
        @SuppressWarnings("unchecked")
        Collection<Tooth> tooths = (Collection<Tooth>) getValue();

        if ( tooths == null ) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Tooth tooth : tooths) {
            sb.append(tooth.toString());
        }

        return sb.toString();
    }
}
