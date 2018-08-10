package org.aion.avm.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.aion.avm.core.classloading.AvmClassLoader;
import org.aion.avm.core.persistence.LoadedDApp;
import org.aion.avm.core.util.Helpers;
import org.aion.kernel.KernelInterface;


/**
 * This is just a utility class which contains the logic required to assemble a LoadedDApp instance from the code storage in KernelInterface
 * or construct a temporary LoadedDApp instance from transformed classes, in-memory.
 * This logic was formally in DAppExecutor/DAppCreator but moving it out made handling the cached DApp case less specialized.
 */
public class DAppLoader {
    /**
     * Called to load an immortal DApp from the code storage provided by the kernel.
     * 
     * @param kernel The storage abstraction.
     * @param address The DApp address.
     * @return The DApp instance.
     * @throws IOException If there was a failure decoding the code from the kernel.
     */
    public static LoadedDApp loadFromKernel(KernelInterface kernel, byte[] address) throws IOException {
        // First, we need to load the DApp bytecode.
        byte[] immortalDappJar = kernel.getTransformedCode(address);
        ImmortalDappModule app = ImmortalDappModule.readFromJar(immortalDappJar);
        
        // We now need all the classes which will loaded within the class loader for this DApp (includes Helper and userlib classes we add).
        Map<String, byte[]> allClasses = Helpers.mapIncludingHelperBytecode(app.classes);
        
        // Construct the per-contract class loader.
        AvmClassLoader classLoader = NodeEnvironment.singleton.createInvocationClassLoader(allClasses);
        
        // Load all the user-defined classes (these are required for both loading and storing state).
        // (we do this in alphabetical order since the persistence model needs consistent read/write order).
        List<Class<?>> aphabeticalContractClasses = Helpers.getAlphabeticalUserTransformedClasses(classLoader, allClasses.keySet());
        
        // We now have all the information to describe the LoadedDApp.
        return new LoadedDApp(classLoader, address, aphabeticalContractClasses, app.mainClass);
    }

    /**
     * Called to create a temporary DApp from transformed classes, in-memory.
     * 
     * @param app The transformed module.
     * @param address The DApp address.
     * @return The DApp instance.
     */
    public static LoadedDApp fromTransformed(TransformedDappModule app, byte[] address) {
        // We now need all the classes which will loaded within the class loader for this DApp (includes Helper and userlib classes we add).
        Map<String, byte[]> allClasses = Helpers.mapIncludingHelperBytecode(app.classes);
        
        // Construct the per-contract class loader.
        AvmClassLoader classLoader = NodeEnvironment.singleton.createInvocationClassLoader(allClasses);
        
        // Load all the user-defined classes (these are required for both loading and storing state).
        // (we do this in alphabetical order since the persistence model needs consistent read/write order).
        List<Class<?>> aphabeticalContractClasses = Helpers.getAlphabeticalUserTransformedClasses(classLoader, allClasses.keySet());
        
        // We now have all the information to describe the LoadedDApp.
        return new LoadedDApp(classLoader, address, aphabeticalContractClasses, app.mainClass);
    }
}