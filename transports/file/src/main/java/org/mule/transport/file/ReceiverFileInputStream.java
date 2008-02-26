
package org.mule.transport.file;

import org.mule.api.DefaultMuleException;
import org.mule.transport.file.i18n.FileMessages;
import org.mule.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This implementation is used when streaming and will move or delete the source file
 * when the stream is closed.
 */
class ReceiverFileInputStream extends FileInputStream
{
    private File currentFile;
    private boolean deleteOnClose;
    private File moveToOnClose;

    public ReceiverFileInputStream(File currentFile, boolean deleteOnClose, File moveToOnClose)
        throws FileNotFoundException
    {
        super(currentFile);
        this.currentFile = currentFile;
        this.deleteOnClose = deleteOnClose;
        this.moveToOnClose = moveToOnClose;
    }

    public void close() throws IOException
    {
        super.close();

        if (moveToOnClose != null)
        {
            FileUtils.moveFile(currentFile, moveToOnClose);
        }
        else if (deleteOnClose)
        {
            if (!currentFile.delete())
            {
                try
                {
                    throw new DefaultMuleException(
                        FileMessages.failedToDeleteFile(currentFile.getAbsolutePath()));
                }
                catch (DefaultMuleException e)
                {
                    IOException e2 = new IOException();
                    e2.initCause(e);
                    throw e2;
                }
            }
        }
    }

    public File getCurrentFile()
    {
        return currentFile;
    }

}
