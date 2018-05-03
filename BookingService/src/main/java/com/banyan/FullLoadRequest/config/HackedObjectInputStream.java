package com.banyan.FullLoadRequest.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Booking.AuthenticationData;
import com.banyan.FullLoadRequest.models.Booking.BillTo;
import com.banyan.FullLoadRequest.models.Booking.Consignee;
import com.banyan.FullLoadRequest.models.Booking.ConsigneeAccessorials;
import com.banyan.FullLoadRequest.models.Booking.Contact;
import com.banyan.FullLoadRequest.models.Booking.Dock;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Booking.InsuranceInfo;
import com.banyan.FullLoadRequest.models.Booking.LoadAccessorials;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;
import com.banyan.FullLoadRequest.models.Booking.PackageInfo;
import com.banyan.FullLoadRequest.models.Booking.ParcelOptions;
import com.banyan.FullLoadRequest.models.Booking.Product;
import com.banyan.FullLoadRequest.models.Booking.RateServices;
import com.banyan.FullLoadRequest.models.Booking.ReferenceField;
import com.banyan.FullLoadRequest.models.Booking.Shipper;
import com.banyan.FullLoadRequest.models.Booking.ShipperAccessorials;
import com.banyan.FullLoadRequest.models.Booking.UserDefined;

public class HackedObjectInputStream extends ObjectInputStream {

    public HackedObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();

        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.FullLoad_Request"))
            resultClassDescriptor = ObjectStreamClass.lookup(FullLoad_Request.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.AuthenticationData"))
            resultClassDescriptor = ObjectStreamClass.lookup(AuthenticationData.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Address"))
            resultClassDescriptor = ObjectStreamClass.lookup(Address.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.BillTo"))
            resultClassDescriptor = ObjectStreamClass.lookup(BillTo.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Consignee"))
            resultClassDescriptor = ObjectStreamClass.lookup(Consignee.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Shipper"))
            resultClassDescriptor = ObjectStreamClass.lookup(Shipper.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.ConsigneeAccessorials"))
            resultClassDescriptor = ObjectStreamClass.lookup(ConsigneeAccessorials.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.ShipperAccessorials"))
            resultClassDescriptor = ObjectStreamClass.lookup(ShipperAccessorials.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Contact"))
            resultClassDescriptor = ObjectStreamClass.lookup(Contact.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Dock"))
            resultClassDescriptor = ObjectStreamClass.lookup(Dock.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.LoadAccessorials"))
            resultClassDescriptor = ObjectStreamClass.lookup(LoadAccessorials.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Loadinfo"))
            resultClassDescriptor = ObjectStreamClass.lookup(Loadinfo.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.PackageInfo"))
            resultClassDescriptor = ObjectStreamClass.lookup(PackageInfo.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.ParcelOptions"))
            resultClassDescriptor = ObjectStreamClass.lookup(ParcelOptions.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.Product"))
            resultClassDescriptor = ObjectStreamClass.lookup(Product.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.ReferenceField"))
            resultClassDescriptor = ObjectStreamClass.lookup(ReferenceField.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.UserDefined"))
            resultClassDescriptor = ObjectStreamClass.lookup(UserDefined.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.RateServices"))
            resultClassDescriptor = ObjectStreamClass.lookup(RateServices.class);
        
        if (resultClassDescriptor.getName().equals("com.banyan.FullLoadRequest.models.InsuranceInfo"))
            resultClassDescriptor = ObjectStreamClass.lookup(InsuranceInfo.class);

        return resultClassDescriptor;
    }
}
