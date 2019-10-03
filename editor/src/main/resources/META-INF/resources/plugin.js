ckEditor.on(
        'paste',
        function(event) {
            event.stop();
            alert('Please, do not paste code here!');
        }
);